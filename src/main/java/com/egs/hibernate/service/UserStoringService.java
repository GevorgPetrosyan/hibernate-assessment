package com.egs.hibernate.service;


import com.egs.hibernate.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserStoringService {
    void saveUser(User user);
}
