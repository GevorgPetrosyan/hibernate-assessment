package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.Country;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.rest.model.address.AddressResponse;
import com.egs.hibernate.rest.model.country.CountryResponse;
import com.egs.hibernate.rest.model.phone_number.PhoneNumberResponse;
import com.egs.hibernate.rest.model.user.UserCountWithCountryCodeResponse;
import com.egs.hibernate.rest.model.user.UserResponse;
import com.egs.hibernate.rest.model.user.UserSearchRequest;
import com.egs.hibernate.service.UserService;
import com.egs.hibernate.utils.DBConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private static final int DEFAULT_PAGE_NO = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int DEFAULT_MAX_PAGE_SIZE = 100;
    private static final String DESC_SORT = "DESC";

    public static final String DEFAULT_SORT = "ASC";

    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void generateUsers(final int count) {

        int i = userRepository.findFirstUsernameByMaxId()
                .map(it -> it.split("_")[1])
                .map(Integer::valueOf)
                .map(it -> ++it)
                .orElse(1);

        int terminate = i + count;
        for (; i < terminate; i++) {
            final String username = "username_" + i;
            try {
                final User user = constructUser(username);
                final Set<Address> addresses = constructAddresses(user);
                final PhoneNumber phoneNumber = constructPhoneNumber(user);
                user.setPhoneNumbers(Set.of(phoneNumber));
                user.setAddresses(addresses);
                userRepository.save(user);
            } catch (final Exception e) {
                log.warn("User with username: {} can't be created. {}", username, e.getMessage());
            }
        }
    }

    @Override
    public void createUser() {
        int i = userRepository.findFirstUsernameByMaxId()
                .map(it -> it.split("_")[1])
                .map(Integer::valueOf)
                .map(it -> ++it)
                .orElse(0);
        final String username1 = "username_" + i;
        User user1 = saveUser(username1);
        log.info("user : {} successfully created", user1.getId());
        final String username2 = "username_" + (i + 1);
        final User user2 = constructUser(username2);
        userRepository.save(user2);
        throw new RuntimeException("Please help to save user1 !!!");
    }

    public User saveUser(String username) {
        final User user = constructUser(username);
        return userRepository.save(user);
    }

    @Override
    public Page<UserResponse> getAll(final UserSearchRequest request) {

        if (request.getPageNo() < 1) {
            return new PageImpl<>(new ArrayList<>());
        }

        final List<UserResponse> userResponses = userRepository
                .search(normalizeRequestData(request))
                .stream()
                .map(this::convertUserToUserResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(userResponses);
    }

    @Override
    public List<UserCountWithCountryCodeResponse> getUsersCountWithCountryCode() {
        return userRepository.getUsersCountWithCountryCode();
    }

    @Override
    public List<String> getCountryWithManyUsers() {
        return userRepository.getCountryWithManyUsers();
    }

    private UserResponse convertUserToUserResponse(User user) {

        final UserResponse userResponse = new UserResponse();

        userResponse.setUsername(user.getUsername());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setBirthdate(user.getBirthdate());
        userResponse.setAddresses(user.getAddresses()
                .stream()
                .map(this::convertAddressToAddressResponse)
                .collect(Collectors.toSet()));
        userResponse.setPhoneNumbers(user.getPhoneNumbers()
                .stream()
                .map(this::convertPhoneNumberToPhoneNumberResponse)
                .collect(Collectors.toSet()));

        return userResponse;
    }

    private AddressResponse convertAddressToAddressResponse(final Address address) {
        final AddressResponse response = new AddressResponse();

        response.setCity(address.getCity());
        response.setStreet(address.getStreet());
        response.setPostalCode(address.getPostalCode());
        response.setAddressLine1(address.getAddressLine1());
        response.setAddressLine2(address.getAddressLine2());
        response.setCountry(convertCountryToCountryResponse(address.getCountry()));

        return response;
    }

    private PhoneNumberResponse convertPhoneNumberToPhoneNumberResponse(final PhoneNumber phoneNumber) {
        final PhoneNumberResponse response = new PhoneNumberResponse();

        response.setPhoneNumber(phoneNumber.getPhoneNumber());

        return response;
    }

    private CountryResponse convertCountryToCountryResponse(final Country country) {
        final CountryResponse response = new CountryResponse();

        response.setCountryCode(country.getCountryCode());
        response.setDisplayName(country.getDisplayName());

        return response;
    }

    private static PhoneNumber constructPhoneNumber(User user) {
        return PhoneNumber.builder().phoneNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 999999999L)))
                .user(user).build();
    }

    private Set<Address> constructAddresses(User user) {
        return RandomAddress.get().listOf(2).parallelStream()
                .map(fakeAddress -> Address.builder().city(fakeAddress.getCity()).postalCode(fakeAddress.getPostalCode())
                        .country(countryRepository.findById(ThreadLocalRandom.current().nextLong(1L, 272L)).orElse(null))
                        .user(user).build()).collect(Collectors.toSet());
    }

    private static User constructUser(String username) {
        final Person person = RandomPerson.get().next();
        return User.builder().firstName(person.getFirstName())
                .lastName(person.getLastName()).username(username)
                .birthdate(person.getBirthdate().toLocalDate()).build();
    }

    private UserSearchRequest normalizeRequestData(final UserSearchRequest request) {

        final UserSearchRequest normalizeRequest = new UserSearchRequest();

        normalizeRequest.setSortBy(getSortDirection(request.getSortBy()));
        normalizeRequest.setSortColumn(getColumn(request.getSortColumn()));
        normalizeRequest.setPageNo(getPageNumber(request.getPageNo()));
        normalizeRequest.setPageSize(getPageSize(request.getPageSize()));

        normalizeRequest.setUsername(request.getUsername());
        normalizeRequest.setFirstName(request.getFirstName());
        normalizeRequest.setLastName(request.getLastName());
        normalizeRequest.setBirthdate(request.getBirthdate());

        return normalizeRequest;
    }

    private Integer getPageSize(Integer pageSize) {
        if (pageSize == null || pageSize < DEFAULT_PAGE_NO) {
            return DEFAULT_PAGE_SIZE;
        } else if (pageSize > DEFAULT_MAX_PAGE_SIZE) {
            return DEFAULT_MAX_PAGE_SIZE;
        }
        return pageSize;
    }

    private Integer getPageNumber(Integer pageNo) {
        if (pageNo == null || pageNo < DEFAULT_PAGE_NO) {
            return DEFAULT_PAGE_NO;
        }
        return pageNo;
    }

    private String getSortDirection(final String sortDirection) {

        if (sortDirection.equalsIgnoreCase(DESC_SORT)) {
            return DESC_SORT;
        }

        return DEFAULT_SORT;
    }

    private String getColumn(final String column) {

        if (column.equalsIgnoreCase(DBConstants.COLUMN_USERNAME)) {
            return DBConstants.COLUMN_USERNAME;
        } else if (column.equalsIgnoreCase(DBConstants.COLUMN_FIRSTNAME)) {
            return DBConstants.COLUMN_FIRSTNAME;
        } else if (column.equalsIgnoreCase(DBConstants.COLUMN_LASTNAME)) {
            return DBConstants.COLUMN_LASTNAME;
        } else if (column.equalsIgnoreCase(DBConstants.COLUMN_BIRTHDATE)) {
            return DBConstants.COLUMN_BIRTHDATE;
        }
        return DBConstants.COLUMN_ID;
    }
}
