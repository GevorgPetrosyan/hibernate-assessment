package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.mapper.Mapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final CountryRepository countryRepository;

    private final Mapper mapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void generateUsers(final int count) {
        /**
         * instead of select the whole user entity from db, selects only the username
         */
        int i = 0;
        final String lastUsername = userRepository.getUsernameByOrderByIdDesc();
        if (lastUsername != null) {
            String split = lastUsername.split("_")[1];
            i = Integer.parseInt(split);
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

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void generateUsersWithMultithreading(final int count) {
        /**
         * instead of select the whole user entity from db, selects only the username
         */
        int i = 0;
        final String lastUsername = userRepository.getUsernameByOrderByIdDesc();
        if (lastUsername != null) {
            String split = lastUsername.split("_")[1];
            i = Integer.parseInt(split);
            ++i;
        }

        final int terminate = i + count;

        final ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future> futures = new ArrayList<>();

        for (; i < terminate; i++) {
            final String username = "username_" + i;
            try {
                final Future<?> submit = executor.submit(() -> {
                    final User user = constructUser(username);
                    final Set<Address> addresses = constructAddresses(user);
                    final PhoneNumber phoneNumber = constructPhoneNumber(user);
                    user.setPhoneNumbers(Set.of(phoneNumber));
                    user.setAddresses(addresses);

                    userRepository.save(user);
                });
                futures.add(submit);
            } catch (final Exception e) {
                log.warn("User with username: {} can't be created. {}", username, e.getMessage());
            }
        }

        while (true) {
            if (futures.stream().allMatch(Future::isDone)) {
                break;
            }
        }
    }

    /**
     * Get All Users(username, firstName, lastName, birthdate)
     * @param page
     * @param size
     * @param sortBy
     * @return
     */
    @Override
    public Page<UserResponseModel> getAll(final int page, final int size, final String sortBy) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.getAll(pageable);
    }

    /**
     * Get All Users(username, firstName, lastName, birthdate, addresses, phone numbers)
     * @param page
     * @param size
     * @param sortBy
     * @return
     */
    @Override
    public Page<UserFullResponseModel> getAllUsers(final int page, final int size, final String sortBy) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        final Page<User> users = userRepository.getAllUsers(pageable);

        final List<UserFullResponseModel> responseModelList = users.stream().map((user -> {
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

        return new PageImpl<>(responseModelList, pageable, users.getTotalElements());
    }

    /**
     * Get Count of users by country (count of users, country)
     * @return
     */
    @Override
    public List<UserCountryResponseModel> getCountOfUsersByCountry() {
        return userRepository.getCountOfUsersByCountry();
    }


    private static PhoneNumber constructPhoneNumber(User user) {
        return PhoneNumber.builder().phoneNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 999999999L)))
                .user(user)
                .build();
    }

    private Set<Address> constructAddresses(User user) {
        return RandomAddress.get().listOf(2).stream()
                .map(fakeAddress -> Address.builder().city(fakeAddress.getCity()).postalCode(fakeAddress.getPostalCode())
                        .country(countryRepository.findById(ThreadLocalRandom.current().nextLong(1L, 272L)).orElse(null))
                        .user(user)
                        .build())
                .collect(Collectors.toSet());
    }

    private static User constructUser(String username) {
        final Person person = RandomPerson.get().next();
        return User.builder().firstName(person.getFirstName())
                .lastName(person.getLastName()).username(username)
                .birthdate(person.getBirthdate().toLocalDate()).build();
    }
}
