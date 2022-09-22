package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.dto.UserByCountryDto;
import com.egs.hibernate.dto.UserDto;
import com.egs.hibernate.dto.response.UserByCountryResponse;
import com.egs.hibernate.dto.response.UserResponse;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.Country;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.mapper.UserMapper;
import com.egs.hibernate.repository.AddressRepository;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.repository.PhoneNumberRepository;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final CountryRepository countryRepository;
  private List<Country> countries;
  private final UserMapper userMapper;
  private final AddressRepository addressRepository;
  private final PhoneNumberRepository phoneNumberRepository;
  private final EntityManager entityManager;

  @Override
  @Transactional(propagation = Propagation.SUPPORTS)
  public void generateUsers(final int count) {
    countries = countryRepository.findAll();
    int i = userRepository.findFirstByOrderByIdDesc()
        .map(User::getUsername)
        .map(it -> it.split("_")[1])
        .map(Integer::valueOf)
        .map(it -> ++it)
        .orElse(0);
    final int terminate = i + count;
    List<User> users = new ArrayList<>();
    for (; i < terminate; i++) {
      final String username = "username_" + i;
      try {
        final User user = constructUser(username);
        final Set<Address> addresses = constructAddresses(user);
        final PhoneNumber phoneNumber = constructPhoneNumber(user);
        addresses.forEach(address -> {
          address.setUser(user);
          addressRepository.save(address);
        });
        phoneNumber.setUser(user);
        phoneNumberRepository.save(phoneNumber);
        users.add(user);
      } catch (final Exception e) {
        log.warn("User with username: {} can't be created. {}", username, e.getMessage());
      }
    }
    int batchSize = 2000;
    for (int j = 0; j < users.size(); j = j + batchSize) {
      if (j + batchSize > users.size()) {
        List<User> userList = users.subList(j, users.size() - 1);
        userRepository.saveAll(userList);
        break;
      }
      List<User> finalList = users.subList(j, j + batchSize);
      userRepository.saveAll(finalList);
    }

  }

  private static PhoneNumber constructPhoneNumber(User user) {
    return PhoneNumber.builder()
        .phoneNumber(String.valueOf(ThreadLocalRandom.current().nextLong(100000000L, 999999999L)))
        .user(user).build();
  }

  private Set<Address> constructAddresses(User user) {
    return RandomAddress.get().listOf(2).stream()
        .map(fakeAddress -> Address.builder().city(fakeAddress.getCity())
            .postalCode(fakeAddress.getPostalCode())
            .country(countries.stream().filter(
                country -> country.getId() == ThreadLocalRandom.current().nextLong(1L, 272L))
                .findFirst().orElse(null))
            .user(user).build()).collect(Collectors.toSet());
  }

  private static User constructUser(String username) {
    final Person person = RandomPerson.get().next();
    return User.builder().firstName(person.getFirstName())
        .lastName(person.getLastName()).username(username)
        .birthdate(person.getBirthdate().toLocalDate()).build();
  }


  @Override
  public List<UserDto> findAllUsers(int page, int size, String field) {
    PageRequest pageRequest = PageRequest.of(page, size, Sort.by(field));
    return userRepository.findAllUsers(pageRequest);
  }

  @Override
  public List<UserResponse> findAll(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    return userRepository.findAll(pageRequest)
        .stream()
        .map(userMapper::toResponse)
        .collect(Collectors.toList());
  }

  @Override
  public List<UserByCountryDto> findAllGroupByCountryCode() {
    Session session = entityManager.unwrap(Session.class);
    Query query = session
        .createQuery("select c.countryCode as countryCode, count(distinct a.user.id) as count"
            + " from address a left join users u on  u.id = a.user.id left join country c on a.country.id = c.id "
            + " where a.country.id is not null "
            + " group by c.countryCode");
    return (List<UserByCountryDto>) query.list();
  }
}
