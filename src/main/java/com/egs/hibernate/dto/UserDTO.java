package com.egs.hibernate.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private Set<PhoneNumberDTO> phoneNumbers;

    private Set<AddressDTO> addresses;
}
