package com.egs.hibernate.assigment.core.mapper;

import com.egs.hibernate.assigment.data.transfer.response.CountryCodeResponse;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Service;

@Service
public class CountryCodeMapper {

    public CountryCodeResponse toResponse(CountryCode countryCode){
        return CountryCodeResponse.builder()
                .code(countryCode.toString())
                .name(countryCode.getName())
                .alpha3(countryCode.getAlpha3())
                .numeric(countryCode.getNumeric())
                .assignment(countryCode.getAssignment())
                .build();
    }
}
