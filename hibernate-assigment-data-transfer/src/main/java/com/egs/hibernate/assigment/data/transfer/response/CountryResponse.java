package com.egs.hibernate.assigment.data.transfer.response;

import com.neovisionaries.i18n.CountryCode;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CountryResponse {

    private String displayName;

    private CountryCode countryCode;
}
