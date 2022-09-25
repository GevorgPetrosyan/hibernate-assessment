package com.egs.hibernate.assigment.data.transfer.response;

import com.neovisionaries.i18n.CountryCode;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CountryCodeResponse {

    private String code;
    private String name;
    private String alpha3;
    private Integer numeric;
    private CountryCode.Assignment assignment;
}
