package com.egs.hibernate.response.projection;

import java.time.LocalDate;

public interface UserProjection {

    String getUsername();

    String getFirstName();

    String getLastName();

    LocalDate getBirthdate();

}
