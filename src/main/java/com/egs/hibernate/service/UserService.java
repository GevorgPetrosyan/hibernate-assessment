package com.egs.hibernate.service;

import com.egs.hibernate.rest.model.country.CountryCodeWithManyUsersResponse;
import com.egs.hibernate.rest.model.user.UserCountWithCountryCodeResponse;
import com.egs.hibernate.rest.model.user.UserResponse;
import com.egs.hibernate.rest.model.user.UserSearchRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void generateUsers(int count);

    Page<UserResponse> getAll(final UserSearchRequest request);

    List<UserCountWithCountryCodeResponse> getUsersCountWithCountryCode();

    List<CountryCodeWithManyUsersResponse> getCountryWithManyUsers();
}
