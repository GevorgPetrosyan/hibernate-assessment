package com.egs.hibernate.rest.model.user;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Arman Karapetyan
 * @date 2022-09-15
 **/

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserCountWithCountryCodeResponse {

    private CountryCode countryCode;
    private Long userCount;

}
