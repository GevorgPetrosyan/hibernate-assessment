package com.egs.hibernate.model;

import com.egs.hibernate.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressResponse {

    private String city;
    private String street;
    private String postalCode;
    private String countryName;
}
