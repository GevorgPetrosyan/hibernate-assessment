package com.egs.hibernate.model;

import com.egs.hibernate.entity.Country;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Tigran Hovhannisyan
 * @Date 16/09/2022
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponseModel {

    private String street;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String postalCode;

    private Country country;
}
