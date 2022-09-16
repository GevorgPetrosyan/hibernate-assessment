package com.egs.hibernate.service;

import com.egs.hibernate.model.UserCountryResponseModel;
import com.egs.hibernate.model.UserFullResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public interface UserService {

    void generateUsers(int count);

    void generateUsersWithMultithreading(int count);

    Page<UserResponseModel> getAll(int page, int size, String sortBy);

    Page<UserFullResponseModel> getAllUsers(int page, int size, String sortBy);

    List<UserCountryResponseModel> getCountOfUsersByCountry();
}
