package com.egs.hibernate.service;

import com.egs.hibernate.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    void generateUsers(int count);
    Page<UserDTO> usersFilter(Integer pageNo, Integer pageSize, String columnName);
}
