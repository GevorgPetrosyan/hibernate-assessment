package com.egs.hibernate.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

    private String street;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postalCode;

    private CountryResponse country;

}
