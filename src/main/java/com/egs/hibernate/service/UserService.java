package com.egs.hibernate.service;

import com.egs.hibernate.response.ResponseUser;
import org.springframework.data.domain.Page;

public interface UserService {

    void generateUsers(int count);

    void createUser();

    Page<ResponseUser> getAll(Integer pageNo, Integer pageSize, String sortBy);
}
