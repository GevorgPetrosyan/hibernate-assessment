package com.egs.hibernate.assigment.core.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.assigment.core.entity.Address;
import com.egs.hibernate.assigment.core.entity.PhoneNumber;
import com.egs.hibernate.assigment.core.entity.User;
import com.egs.hibernate.assigment.core.mapper.UserMapper;
import com.egs.hibernate.assigment.core.repository.CountryRepository;
import com.egs.hibernate.assigment.core.repository.UserRepository;
import com.egs.hibernate.assigment.core.service.UserService;
import com.egs.hibernate.assigment.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
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
    private final UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    public void generateUsers(final int count) {
        int i = userRepository.findFirstByOrderByCreatedDesc()
                .map(User::getUsername)
                .map(it -> it.split("_")[1])
                .map(Integer::valueOf)
                .map(it->++it)
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
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toResponseList(users);
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
