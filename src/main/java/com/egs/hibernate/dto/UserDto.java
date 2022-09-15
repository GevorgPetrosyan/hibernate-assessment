package com.egs.hibernate.dto;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Artur Tomeyan
 * @date 08/09/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {

    private String username;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;

//    private String city;
//    private String street;
//    private String address_line_1;
//    private String address_line_2;
//    private String postalCode;
//    private CountryCode country;
//    private String phoneNumber;

    private AddressDto address;
    private PhoneNumberDto phoneNumber;

}
