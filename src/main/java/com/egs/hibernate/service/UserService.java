package com.egs.hibernate.service;

import com.egs.hibernate.dto.UserDto;
import java.util.List;

public interface UserService {
    void generateUsers(int count);

    List<UserDto>findAllUsers(int page, int size,String field);
}


