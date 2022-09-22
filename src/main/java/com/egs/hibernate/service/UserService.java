package com.egs.hibernate.service;

import com.egs.hibernate.response.CountryCodeResponse;
import com.egs.hibernate.response.ResponseUser;
import org.springframework.data.domain.Page;

import java.util.List;


public interface UserService {

    void generateUsers(int count);

    void createUser();

    Page<ResponseUser> getAll(Integer pageNo, Integer pageSize, String sortBy);

    Page<ResponseUser> getByPages(Integer pageNo, Integer pageSize, String sortBy);

    List<CountryCodeResponse> getUsersCountByCountryCode();
}
