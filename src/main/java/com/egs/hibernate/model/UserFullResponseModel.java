package com.egs.hibernate.model;

import com.neovisionaries.i18n.CountryCode;
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
public class UserFullResponseModel {

    private String username;

    private String firstName;

    private String lastName;

    private LocalDate birthdate;

    private String street;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postalCode;

    private CountryCode countryCode;

    private String displayName;

    private String phoneNumber;
}
