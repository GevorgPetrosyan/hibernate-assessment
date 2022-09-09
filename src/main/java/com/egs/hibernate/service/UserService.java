package com.egs.hibernate.service;

import com.egs.hibernate.dtos.UserDto;

import java.util.List;

public interface UserService {
    void generateUsers(int count);

    List<UserDto> getAllUsers(int pageNo, int pageSize, String sortBy);
}
