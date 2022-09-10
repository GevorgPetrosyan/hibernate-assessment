package com.egs.hibernate.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {
  private String username;
  private String firstName;
  private String lastName;
  private LocalDate birthdate;
}