package com.egs.hibernate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String username;

    private LocalDate birthdate;

    private String firstName;

    private String lastName;

    private Set<PhoneNumberResponse> phoneNumbers;

    private Set<AddressResponse> addresses;
}
