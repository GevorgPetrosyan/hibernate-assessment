package com.egs.hibernate.rest.model.user;

import com.egs.hibernate.rest.model.address.AddressResponse;
import com.egs.hibernate.rest.model.phone_number.PhoneNumberResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Arman Karapetyan
 * @date 2022-09-08
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponse {
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

    private Set<AddressResponse> addresses;
    private Set<PhoneNumberResponse> phoneNumbers;

}
