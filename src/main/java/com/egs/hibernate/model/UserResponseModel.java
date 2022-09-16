package com.egs.hibernate.model;

import java.time.LocalDate;

/**
 * @author Vahan Grigoryan
 * @Date 9/9/2022
 */

public interface UserResponseModel {

    String getUsername();

    String getFirstName();

    String getLastName();

    LocalDate getBirthdate();
}
