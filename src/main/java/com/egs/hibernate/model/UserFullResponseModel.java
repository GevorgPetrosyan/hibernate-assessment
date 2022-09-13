package com.egs.hibernate.model;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

/**
 * @author Vahan Grigoryan
 * @Date 9/12/2022
 */

@Getter
@Setter
@AllArgsConstructor
public class UserFullResponseModel {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private String phoneNumber;

    private String street;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postalCode;

    private CountryCode countryCode;

    private String displayName;
}
