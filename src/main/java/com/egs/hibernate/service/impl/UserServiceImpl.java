package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.mapper.UserToResponseMapper;
import com.egs.hibernate.model.UserCountryResponseModel;
import com.egs.hibernate.model.UserFullResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;

    private final UserToResponseMapper mapper;

    @Override
    /**
     * Changed propagation from REQUIRES_NEW to REQUIRED
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void generateUsers(final int count) {
        /**
         * The written code takes user entity from db, but in this case we need only username of user
         */
//        int i = userRepository.findFirstByOrderByCreatedDesc()
//                .map(User::getUsername)
//                .map(it -> it.split("_")[1])
//                .map(Integer::valueOf)
//                .map(it->++it)
//                .orElse(0);

        /**
         * So I modify this part of code by creating native sql query in UserRepository
         */

        String userName = userRepository.getUserNameFindFirstByOrderByIdDesc();
        int i = 1;
        if (userName != null) {
            String s = userName.split("_")[1];
            i = Integer.parseInt(s);
            ++i;
        }

        final int terminate = i + count;
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

    /**
     * Retrieve username, firstName, lastName and birthdate of User
     */
    public Page<UserResponseModel> getUserResponseModel(Integer pageNumber, Integer pageSize, String sortBy){

        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

        return userRepository.getUserResponseModel(paging);
    }

    /**
     * Retrieve username, firstName, lastName, birthdate, addresses and phoneNumbers of User
     */
    @Override
    public Slice<UserFullResponseModel> getUserFullResponseModel(Integer pageNumber, Integer pageSize, String sortBy) {

        final Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        final Slice<User> users = userRepository.getUserFullResponseModel(pageable);

        final List<UserFullResponseModel> responseModelList = users.stream().map(
                (user -> {
            final Set<Address> addresses = user.getAddresses();
            final Set<PhoneNumber> phoneNumbers = user.getPhoneNumbers();
            return new UserFullResponseModel(
                    user.getUsername(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getBirthdate(),
                    mapper.mapToAddressResponseModelSet(addresses),
                    mapper.mapToPhoneNumberResponseModelSet(phoneNumbers)
            );
        })).collect(Collectors.toList());

        return new SliceImpl<>(responseModelList, pageable, users.hasNext());
    }

    /**
     * Retrieve count of users by countries and country codes
     */
    @Override
    public List<UserCountryResponseModel> getCountOfUsersByCountry() {

        return userRepository.getCountOfUsersByCountry();
    }

    private static PhoneNumber constructPhoneNumber(User user) {
        return PhoneNumber.builder().phoneNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 999999999L)))
                .user(user).build();
    }

    private Set<Address> constructAddresses(User user) {
        return RandomAddress.get().listOf(2).stream()
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
}
