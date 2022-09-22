package com.egs.hibernate.service;

import com.egs.hibernate.dto.UserDto;
import com.egs.hibernate.dto.UserByCountryDto;
import com.egs.hibernate.dto.response.UserByCountryResponse;
import com.egs.hibernate.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    void generateUsers(int count);

    List<UserDto>findAllUsers(int page, int size,String field);

    List<UserResponse> findAll(int page, int size);

  List<UserByCountryDto> findAllGroupByCountryCode();
}


