package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.service.UserStoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserStoringServiceImpl implements UserStoringService {

    private final UserRepository userRepository;

    @Autowired
    public UserStoringServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUser(User user) {
         userRepository.save(user);
    }
}
