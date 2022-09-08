package com.egs.hibernate.service;

import com.egs.hibernate.dto.projection.UserProjectionDto;

import java.util.List;

public interface UserService {
    void generateUsers(int count);

    List<UserProjectionDto> findAllUsers(int page, int size,String firstName);
}
