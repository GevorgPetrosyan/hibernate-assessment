package com.egs.hibernate.service;

import com.egs.hibernate.model.CountryCodeResponse;
import com.egs.hibernate.model.UserResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void generateUsers(int count);

    Page<UserResponse> getUsersByPage(Integer pageNo, Integer pageSize, String sortBy);

    List<CountryCodeResponse> getUsersByCountryCode();

}
