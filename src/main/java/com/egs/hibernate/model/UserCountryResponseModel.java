package com.egs.hibernate.model;

import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Vahan Grigoryan
 * @Date 9/14/2022
 */

public interface UserCountryResponseModel {

    CountryCode getCountryCode();

    Long getCount();
}
