package com.egs.hibernate.service;

import com.egs.hibernate.dto.projection.UserProjectionDto;
import com.egs.hibernate.dto.response.UserCountByCountryCode;
import com.egs.hibernate.dto.response.UserResponse;
import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public interface UserService {
    void generateUsers(int count);

    List<UserResponse> findAllUsers(int page, int size, String firstName);

    UserCountByCountryCode findAllUsersByCountryId(String code);

    List<CountryCode> findAllCountriesByUserCount();
}
