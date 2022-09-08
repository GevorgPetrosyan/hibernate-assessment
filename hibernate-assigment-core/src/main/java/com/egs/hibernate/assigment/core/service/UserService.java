package com.egs.hibernate.assigment.core.service;

import com.egs.hibernate.assigment.data.transfer.response.UserResponse;

import java.util.List;

public interface UserService {
    void generateUsers(int count);

    List<UserResponse> findAll();
}
