package com.egs.hibernate.dto.projection;

import java.time.LocalDate;

public interface UserProjectionDto {

    String getUsername();

    String getFirstName();

    String getLastName();

    LocalDate getBirthdate();
}
