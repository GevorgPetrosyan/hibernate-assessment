package com.egs.hibernate.rest.model.address;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.rest.model.country.CountryResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Arman Karapetyan
 * @date 2022-09-12
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddressResponse {
    private String street;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String postalCode;
    private CountryResponse country;

}
