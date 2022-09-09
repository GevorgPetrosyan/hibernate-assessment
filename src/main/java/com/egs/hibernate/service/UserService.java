package com.egs.hibernate.service;

import com.egs.hibernate.response.projection.UserProjection;
import com.egs.hibernate.response.UsersCountResponse;

import java.util.List;

public interface UserService {
    void generateUsers(int count);

    List<UserProjection> findAll(int page, int size, String direction, String fieldName);

    UsersCountResponse findUsersCountByCode(String code);
}
