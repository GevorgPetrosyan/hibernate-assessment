package com.egs.hibernate.service.impl;

import com.arakelian.faker.model.Person;
import com.arakelian.faker.service.RandomPerson;
import com.egs.hibernate.entity.User;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.service.TransactionsExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionExecutorImpl implements TransactionsExecutor {

    private final UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public User saveUser(String username) {
        final User user = constructUser(username);
        return userRepository.save(user);
    }

    static User constructUser(String username) {
        final Person person = RandomPerson.get().next();
        return User.builder().firstName(person.getFirstName())
                .lastName(person.getLastName()).username(username)
                .birthdate(person.getBirthdate().toLocalDate()).build();
    }
}
