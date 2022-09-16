package com.egs.hibernate.model;

import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

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

    private Set<AddressResponseModel> addresses;

    private Set<PhoneNumberResponseModel> phoneNumbers;
}
