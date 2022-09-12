package com.egs.hibernate.service;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.rest.model.user.UserSearchRequest;

import java.util.List;

public interface UserSearchRepository {
    List<User> search(UserSearchRequest request);
}
