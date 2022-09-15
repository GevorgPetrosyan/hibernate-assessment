package com.egs.hibernate.model;

import com.egs.hibernate.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Set<AddressResponse> address;
    private Set<PhoneNumberResponse> phoneNumber;


}






