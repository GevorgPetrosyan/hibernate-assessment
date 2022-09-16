package com.egs.hibernate.model;

import com.egs.hibernate.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vahan Grigoryan
 * @Date 9/15/2022
 */

@Getter
@Setter
@AllArgsConstructor
public class AddressResponseModel {

    private String street;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postalCode;

    private Country country;
}
