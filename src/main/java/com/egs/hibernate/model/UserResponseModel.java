package com.egs.hibernate.model;

import java.time.LocalDate;

/**
 * @author Tigran Hovhannisyan
 * @Date 13/09/2022
 */
public interface UserResponseModel {

    String getUsername();

    String getFirstName();

    String getLastName();

    LocalDate getBirthdate();
}
