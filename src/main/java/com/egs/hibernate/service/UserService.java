package com.egs.hibernate.service;

import com.egs.hibernate.model.UserFullResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

public interface UserService {
    void generateUsers(int count);

    Page<UserResponseModel> getUserResponseModel(Integer pageNumber, Integer pageSize, String sortBy);

    Slice<UserFullResponseModel> getUserFullResponseModel(Integer pageNumber, Integer pageSize, String sortBy);
}
