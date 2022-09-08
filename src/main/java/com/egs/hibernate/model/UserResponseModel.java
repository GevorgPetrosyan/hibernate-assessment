package com.egs.hibernate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Tigran Hovhannisyan
 * @Date 13/09/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate date;
}
