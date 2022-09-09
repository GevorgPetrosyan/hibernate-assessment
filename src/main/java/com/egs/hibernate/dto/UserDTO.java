package com.egs.hibernate.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;
}
