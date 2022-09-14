package com.egs.hibernate.model;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vahan Grigoryan
 * @Date 9/14/2022
 */

@Getter
@Setter
@AllArgsConstructor
public class UserCountryResponseModel {

    private CountryCode countryCode;

    private Long count;
}
