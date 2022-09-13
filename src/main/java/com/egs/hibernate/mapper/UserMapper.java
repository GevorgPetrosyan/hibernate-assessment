package com.egs.hibernate.mapper;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.model.UserResponseModel;
import org.springframework.stereotype.Component;

/**
 * @author Vahan Grigoryan
 * @Date 9/9/2022
 */
@Component
public class UserMapper {

    public UserResponseModel mapToResponseModel(final User user) {
        return new UserResponseModel(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate()
        );
    }
}
