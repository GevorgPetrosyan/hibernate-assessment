package com.egs.hibernate.service;

import com.egs.hibernate.dto.UserByCountryDto;
import com.egs.hibernate.dto.UserDto;

import java.util.List;

public interface UserService {
    void generateUsers(int count);

    List<UserDto> getAllUsers(int pageNo, int pageSize, String sortBy);

    List<UserByCountryDto> getCountOfUsersByCountry();

    void createUser();
}