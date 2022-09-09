package com.egs.hibernate.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserSearchCriteria {
    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;
}
