package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomAddress;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.dto.UserDTO;
import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.mapper.UserMapper;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.rest.model.PaginationCriteria;
import com.egs.hibernate.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
  @PersistenceContext
  private EntityManager entityManager;
  private final UserRepository userRepository;
  private final CountryRepository countryRepository;
  private final UserMapper userMapper;
  @Value("${make.flush.indicator.number}")
  private int flushNumber;

  public UserServiceImpl(UserRepository userRepository, CountryRepository countryRepository, UserMapper userMapper) {
    this.userRepository = userRepository;
    this.countryRepository = countryRepository;
    this.userMapper = userMapper;
  }

  @Override
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void generateUsers(final int count) {
    log.info("Attempt to generate users: {}", count);
    int i = userRepository.findUsernameByMaxID()
            .map(it -> it.split("_")[1])
            .map(Integer::valueOf)
            .map(it -> ++it)
            .orElse(1);
    final int terminate = i + count;
    for (; i < terminate; i++) {
      makeFlushIfNecessary(i);
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
  @Transactional(readOnly = true)
  public List<UserDTO> findAll(PaginationCriteria paginationCriteria) {
    log.info("Attempt to produce users, data size: {}", paginationCriteria.getSize());
    Pageable pageable = PageRequest.of(
            paginationCriteria.getPage(),
            paginationCriteria.getSize(),
            Sort.by(paginationCriteria.getSortDirection(),
                    paginationCriteria.getPropertiesForSort().toArray(new String[0])));
    return userRepository.findAll(pageable).stream()
            .map(userMapper::entityToDTO)
            .collect(Collectors.toList());
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

  /**
   * This method make flush and clear․ Works when persists
   * value of ${flushNumber} objects. This action helps
   * avoid from memory leak.
   *
   * @param index loop index
   */
  private void makeFlushIfNecessary(int index) {
    if (index % flushNumber == 0) {
      entityManager.flush();
      entityManager.clear();
    }
  }
}