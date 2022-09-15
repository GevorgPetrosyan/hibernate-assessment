package com.egs.hibernate.service;

public interface UserService {
    void generateUsers(int count);

    void createUser();

    Page<UserResponse> getAllUsers(Integer pageNo, Integer pageSize, String sortBy);

    List<CountOfUsersByCountryCodeResponse> getUsersByCountryCode();

}
