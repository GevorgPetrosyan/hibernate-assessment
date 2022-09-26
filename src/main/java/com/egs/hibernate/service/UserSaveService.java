package com.egs.hibernate.service;

import com.egs.hibernate.entity.User;

public interface UserSaveService {
    User saveUser(String username);
}
