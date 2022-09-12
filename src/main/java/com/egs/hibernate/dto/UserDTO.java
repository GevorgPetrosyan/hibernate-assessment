package com.egs.hibernate.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserDTO {
  private String username;
  private String firstName;
  private String lastName;
  private LocalDate birthdate;
  private Set<AddressDTO> addressDTOS;
  private Set<PhoneNumberDTO> phoneNumberDTOS;
}