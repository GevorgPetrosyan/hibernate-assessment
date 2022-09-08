package com.egs.hibernate.service;

import com.egs.hibernate.dto.ProjectionUserDto;

import java.util.List;

public interface UserService {

    void generateUsers(int count);

    List<ProjectionUserDto> findAllUsers(int page, int size);

}
