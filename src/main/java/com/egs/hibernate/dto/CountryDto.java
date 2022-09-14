package com.egs.hibernate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Artur Tomeyan
 * @date 09/09/2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDto implements Serializable {

    private String countryCode;
    private Integer count;
}