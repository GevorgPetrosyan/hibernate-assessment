package com.egs.hibernate.assigment.core.mapper;

import com.egs.hibernate.assigment.core.entity.User;
import com.egs.hibernate.assigment.core.mapper.base.BaseMapper;
import com.egs.hibernate.assigment.data.transfer.request.UserRequest;
import com.egs.hibernate.assigment.data.transfer.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserMapper implements BaseMapper<User, UserRequest, UserResponse> {

    private final ModelMapper modelMapper;

    @Override
    public User toEntity(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse toResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public List<UserResponse> toResponseList(List<User> userList) {
        return userList.stream()
                .map(this::toResponse)
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
