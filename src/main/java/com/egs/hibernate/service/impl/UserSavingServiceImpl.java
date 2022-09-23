package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.repository.UserRepository;
import com.egs.hibernate.service.UserSavingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSavingServiceImpl implements UserSavingService {

    private final UserRepository userRepository;

    public UserSavingServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
