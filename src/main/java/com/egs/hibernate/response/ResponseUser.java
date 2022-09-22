package com.egs.hibernate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUser {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Set<AddressResponse> addresses;
    private Set<PhoneNumberResponse> phoneNumbers;

}

