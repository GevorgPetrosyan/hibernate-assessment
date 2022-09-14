package com.egs.hibernate.assigment.data.transfer.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressResponse {

    private String street;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postalCode;

    private CountryResponse country;
}
