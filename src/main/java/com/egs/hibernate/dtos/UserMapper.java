package com.egs.hibernate.dtos;

import com.egs.hibernate.entity.User;

/**
 * @author Artur Tomeyan
 * @date 08/09/2022
 */
public class UserMapper {

    public UserDto entityToDto(User user) {

        return new UserDto(user.getUsername(), user.getFirstName(), user.getLastName(), user.getBirthdate());
    }
}
