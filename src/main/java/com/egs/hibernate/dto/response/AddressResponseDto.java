package com.egs.hibernate.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponseDto {

    private String street;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postalCode;

    private CountryResponseDto countryResponseDto;
}
