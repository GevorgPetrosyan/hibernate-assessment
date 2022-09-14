package com.egs.hibernate.assigment.data.transfer.response;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Set;

@Data
@ToString
public class UserResponse {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private Set<String> phoneNumbers;
    private Set<AddressResponse> addresses;
}