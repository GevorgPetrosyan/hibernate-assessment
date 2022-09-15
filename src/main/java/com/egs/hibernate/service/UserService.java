package com.egs.hibernate.service;

import com.egs.hibernate.dto.UserDTO;
import com.egs.hibernate.dto.UsersCountDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void generateUsers(int count);
    Page<UserDTO> usersFilter(Integer pageNo, Integer pageSize, String columnName);
    List<UsersCountDTO> usersCountByCountryCode();
}
