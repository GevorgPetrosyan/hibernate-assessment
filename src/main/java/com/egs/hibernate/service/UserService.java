package com.egs.hibernate.service;

import com.egs.hibernate.rest.model.user.UserResponse;
import com.egs.hibernate.rest.model.user.UserSearchRequest;
import org.springframework.data.domain.Page;

public interface UserService {
    void generateUsers(int count);

    Page<UserResponse> getAll(final UserSearchRequest request);
}
