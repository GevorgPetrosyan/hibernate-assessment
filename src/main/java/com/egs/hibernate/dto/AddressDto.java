package com.egs.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Artur Tomeyan
 * @date 12/09/2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto implements Serializable {
    private String street;
    private String address_line_1;
    private String address_line_2;
    private String city;
    private String postalCode;
}
