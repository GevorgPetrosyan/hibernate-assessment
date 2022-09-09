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
import com.egs.hibernate.response.projection.UserProjection;
import com.egs.hibernate.response.UsersCountResponse;
import com.egs.hibernate.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateUsers(final int count) {
        List<User> users = new ArrayList<>();
        long i = userRepository.findLastUser()
                .map(User::getUsername)
                .map(it -> it.split("_")[1])
                .map(Long::valueOf)
                .map(it -> it + 1L)
                .orElse(1L);
        final long terminate = i + count;
        List<Country> countries = countryRepository.findAll();

        for (; i < terminate; i++) {
            final String username = "username_" + i;
            try {
                Optional<Country> country =
                        countries.stream().filter(c -> c.getId() == ThreadLocalRandom.current().nextLong(1L, 272L)).findFirst();
                final User user = constructUser(username);
                final Set<Address> addresses = constructAddresses(user, country.orElse(null));
                final PhoneNumber phoneNumber = constructPhoneNumber(user);
                user.setPhoneNumbers(Set.of(phoneNumber));
                user.setAddresses(addresses);
                users.add(user);
            } catch (final Exception e) {
                log.warn("User with username: {} can't be created. {}", username, e.getMessage());
            }
        }
        userRepository.saveAll(users);
    }

    @Override
    public List<UserProjection> findAll(int page, int size, String direction, String fieldName) {
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "id");
        if (direction.equalsIgnoreCase(Sort.Direction.ASC.name())) {
            sort = Sort.by(Sort.Direction.ASC, fieldName);
        } else if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())) {
            sort = Sort.by(Sort.Direction.DESC, fieldName);
        }
        return userRepository.findAllUsers(PageRequest.of(page, size, sort));
    }


    @Override
    public UsersCountResponse findUsersCountByCode(String code) {
        Integer allUsersByCountryCode = userRepository.findUsersCountByCountryCode(code);
       return new UsersCountResponse(code,allUsersByCountryCode);
    }

    private static PhoneNumber constructPhoneNumber(User user) {
        return PhoneNumber.builder().phoneNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 999999999L)))
                .user(user).build();
    }

    private Set<Address> constructAddresses(User user, Country country) {
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
