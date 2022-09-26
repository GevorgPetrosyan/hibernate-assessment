package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.dto.UserDTO;
import com.egs.hibernate.dto.UsersCountDTO;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.Country;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.mapper.Mapper;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.service.UserService;
import com.neovisionaries.i18n.CountryCode;
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
    private final UserSaveService s;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateUsers(final int count) {
        int i = userRepository.findFirstByOrderByIdDesc()
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
    @Transactional
    public void createUser() {
        int i = userRepository.findFirstByOrderByIdDesc()
                .map(User::getUsername)
                .map(it -> it.split("_")[1])
                .map(Integer::valueOf)
                .map(it -> ++it)
                .orElse(0);
        final String username1 = "username_" + i;
        User user1 = s.saveUser(username1);
        log.info("user : {} successfully created", user1.getId());
        final String username2 = "username_" + (i + 1);
        final User user2 = constructUser(username2);
        userRepository.save(user2);
        throw new RuntimeException("User2 dont saved !!!");
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDTO> usersSort(Integer pageNo, Integer pageSize, String columnName) {
        log.info("Users filter method started! Attempt to show: {} users sort by: {} ", pageSize, columnName);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(columnName));
        Mapper mapper = new Mapper();
        return new PageImpl<>(mapper.mapToDTOList(userRepository.findAll(paging), UserDTO.class));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsersCountDTO> usersCountByCountryCode() {
        log.info("Users count by country code method start work!");
        return userRepository.usersCountByCountryCode();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryCode> usersCluster() {
        log.info("Country list by users cluster method start work!");
        return userRepository.usersCluster();
    }

    private static PhoneNumber constructPhoneNumber(User user) {
        return PhoneNumber.builder().phoneNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 999999999L)))
                .user(user).build();
    }

    private Set<Address> constructAddresses(User user) {
        Country country = countryRepository.findById(ThreadLocalRandom.current().nextLong(1L, 272L)).orElse(null);
        return RandomAddress.get().listOf(2).stream()
                .map(fakeAddress -> Address.builder().city(fakeAddress.getCity()).postalCode(fakeAddress.getPostalCode())
                        .country(country)
                        .user(user).build()).collect(Collectors.toSet());
    }

    private static User constructUser(String username) {
        final Person person = RandomPerson.get().next();
        return User.builder().firstName(person.getFirstName())
                .lastName(person.getLastName()).username(username)
                .birthdate(person.getBirthdate().toLocalDate()).build();
    }
}
