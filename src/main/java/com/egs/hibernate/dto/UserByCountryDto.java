package com.egs.hibernate.dto;

import com.neovisionaries.i18n.CountryCode;
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
public class UserByCountryDto implements Serializable {

    private CountryCode countryCode;
    private Long count;
}