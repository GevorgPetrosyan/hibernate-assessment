package com.egs.hibernate.service;

import com.egs.hibernate.dto.UserDTO;

import java.util.List;

public interface UserService {
    void generateUsers(int count);
    List<UserDTO> getAllUsers();
}
