package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.response.CountryCodeResponse;
import com.egs.hibernate.response.ResponseUser;
import com.egs.hibernate.service.UserService;
import com.egs.hibernate.utils.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.egs.hibernate.utils.Constants.USERS_TABLE_PAGE_MAX_SIZE;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final Mapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateUsers(final int count) {
        int i = userRepository.findFirstByOrderByIdDescCreatedDesc()
                .map(User::getUsername)
                .map(it -> it.split("_")[1])
                .map(Integer::valueOf)
                .map(it -> ++it)
                .orElse(0);
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

    @Override
    public Page<ResponseUser> getByPages(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo < 1) {
            return new PageImpl<>(Collections.emptyList());
        }
        if (pageSize > USERS_TABLE_PAGE_MAX_SIZE) {
            pageSize = USERS_TABLE_PAGE_MAX_SIZE;
        }
        Pageable paging = PageRequest.of(pageNo - 1, pageSize, Sort.by(sortBy));

        List<ResponseUser> pagedResult = userRepository.findAll(paging).stream().map(mapper::userToResponse).collect(Collectors.toList());
        return new PageImpl<>(pagedResult);
    }

    @Override
    public void createUser() {
//        int i = userRepository.findFirstByOrderByIdDesc()
//                .map(User::getUsername)
//                .map(it -> it.split("_")[1])
//                .map(Integer::valueOf)
//                .map(it -> ++it)
//                .orElse(0);

//        int i = userRepository.findFirstByOrderByIdDesc()
//                .map(User::getUsername)
//                .map(it -> it.split("_")[1])
//                .map(Integer::valueOf)
//                .map(it -> ++it)
//                .orElse(0);
        // TODO Gurgen - fix this code
        int i = -1;

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
    public Page<ResponseUser> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        if (pageNo < 1) {
            return new PageImpl<>(new ArrayList<>());
        }
        Pageable paging = PageRequest.of(pageNo - 1,
                pageSize > 200 ? 200 : pageSize,
                Sort.by(sortBy));

        List<ResponseUser> pagedResult = userRepository.findAll(paging).stream()
                .map(mapper::userToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(pagedResult);
    }

    @Override
    public List<CountryCodeResponse> getUsersCountByCountryCode() {
        return userRepository.findUsersByCountryCode();
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
