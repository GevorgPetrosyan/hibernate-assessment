package com.egs.hibernate.model;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

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

    private Set<AddressResponseModel> addresses;

    private Set<PhoneNumberResponseModel> phoneNumbers;
}
