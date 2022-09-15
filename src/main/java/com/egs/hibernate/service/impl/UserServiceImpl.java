package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.BaseUser;
import com.egs.hibernate.entity.Country;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.exception.CountryByCodeNotFoundException;
import com.egs.hibernate.mapper.UserMapper;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.response.UserResponse;
import com.egs.hibernate.response.UsersCountResponse;
import com.egs.hibernate.service.UserService;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@PersistenceContext
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CountryRepository countryRepository;
    private final EntityManager entityManager;
    private final UserMapper userMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void generateUsers(final int count) {
        List<BaseUser> users = new ArrayList<>();
        long i = userRepository.findLastUser()
                .map(BaseUser::getUsername)
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
                final BaseUser user = constructUser(username);
                final Set<Address> addresses = constructAddresses(user, country.orElse(null));
                final PhoneNumber phoneNumber = constructPhoneNumber();
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
    @Transactional
    public List<UserResponse> findAll(int page, int size, String direction, String fieldName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<BaseUser> query = criteriaBuilder.createQuery(BaseUser.class);
        Root<BaseUser> userRoot = query.from(BaseUser.class);
        if (direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                && fieldName != null && userRoot.get(fieldName) != null) {
            query.orderBy(criteriaBuilder.asc(userRoot.get(fieldName)));
        } else if (direction.equalsIgnoreCase(Sort.Direction.DESC.name())
                && fieldName != null && userRoot.get(fieldName) != null) {
            query.orderBy(criteriaBuilder.desc(userRoot.get(fieldName)));
        } else {
            query.orderBy(criteriaBuilder.asc(userRoot.get("username")));
        }

        List<BaseUser> baseUsers = entityManager
                .createQuery(query)
                .setFirstResult(page)
                .setMaxResults(size).getResultList();

        return baseUsers.stream().map(userMapper::toResponse).collect(Collectors.toList());
    }


    @Override
    public UsersCountResponse findUsersCountByCode(String code) {
        CountryCode country = Arrays.stream(CountryCode.values())
                .filter(countryCode -> countryCode.name().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(CountryByCodeNotFoundException::new);

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<BaseUser> userRoot = criteriaQuery.from(BaseUser.class);
        Root<Address> mapping = criteriaQuery.from(Address.class);
        Root<Country> countryRoot = criteriaQuery.from(Country.class);
        criteriaQuery.select(userRoot.get("id"));
        criteriaQuery.where(
                criteriaBuilder.and(
                        criteriaBuilder.equal(userRoot.get("id"), mapping.get("baseUser")),
                        criteriaBuilder.equal(countryRoot.get("id"), mapping.get("country")),
                        criteriaBuilder.equal(countryRoot.get("countryCode"), country)
                )
        );
        int typedQuery = entityManager.createQuery(criteriaQuery).getResultList().size();
        return new UsersCountResponse(code, typedQuery);
    }

    private static PhoneNumber constructPhoneNumber() {
        return PhoneNumber.builder().phoneNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 999999999L)))
                .build();
    }

    private Set<Address> constructAddresses(BaseUser baseUser, Country country) {
        return RandomAddress.get().listOf(2).stream()
                .map(fakeAddress -> Address.builder().city(fakeAddress.getCity()).postalCode(fakeAddress.getPostalCode())
                        .country(country)
                        .baseUser(baseUser).build()).collect(Collectors.toSet());
    }

    private static BaseUser constructUser(String username) {
        final Person person = RandomPerson.get().next();
        return BaseUser.builder().firstName(person.getFirstName())
                .lastName(person.getLastName()).username(username)
                .birthdate(person.getBirthdate().toLocalDate()).build();
    }

}
