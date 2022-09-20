package com.egs.hibernate.service;

import com.egs.hibernate.response.UserResponse;
import com.egs.hibernate.response.UsersCountResponse;

import java.util.List;

public interface UserService {

    void generateUsers(int count);

    void createUser();

    List<UserResponse> findAll(int page, int size, String direction, String fieldName);

    UsersCountResponse findUsersCountByCode(String code);
}
