package com.egs.hibernate.model;


import com.neovisionaries.i18n.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryCodeResponse {

    private CountryCode countryCode;
    private Long count;
}
