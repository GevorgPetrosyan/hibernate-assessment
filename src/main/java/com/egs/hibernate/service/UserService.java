package com.egs.hibernate.service;

import com.egs.hibernate.dto.UserDTO;
import com.egs.hibernate.rest.model.PaginationCriteria;

import java.util.List;

public interface UserService {

  void generateUsers(int count);

  List<UserDTO> findAll(PaginationCriteria paginationCriteria);
}
