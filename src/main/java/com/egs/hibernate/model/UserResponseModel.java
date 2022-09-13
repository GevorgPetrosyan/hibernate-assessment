package com.egs.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Vahan Grigoryan
 * @Date 9/9/2022
 */
@Getter
@Setter
@AllArgsConstructor
public class UserResponseModel {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;
}
