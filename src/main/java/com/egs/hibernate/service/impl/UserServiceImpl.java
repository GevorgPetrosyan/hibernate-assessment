package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.dto.UserByCountryDto;
import com.egs.hibernate.dto.UserDto;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.mapper.Mapper;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
    private final EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void generateUsers(final int count) {
        int i = userRepository.findFirstByOrderByCreatedDesc()
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

    @Transactional
    @Override
    public List<UserDto> getAllUsers(int pageNo, int pageSize, String sortBy) {

        Mapper mapper = new Mapper();

        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());

        Page<User> all = userRepository.findAll(pageable);

        return all.stream().map(mapper::userEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<UserByCountryDto> getCountOfUsersByCountry() {

        String select = "SELECT DISTINCT new com.egs.hibernate.dto.UserByCountryDto(c.countryCode, count(u.id)) " +
                "FROM users u join address a on u.id = a.user.id " +
                "join country c on c.id = a.country.id group by c.countryCode";

        Query query = entityManager.createQuery(select);
        List<UserByCountryDto> countUsersByCountry = query.getResultList();

        return countUsersByCountry;
    }

    @Override
    public void createUser() {
        int i = userRepository.findFirstByOrderByIdDesc()
                .map(User::getUsername)
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
