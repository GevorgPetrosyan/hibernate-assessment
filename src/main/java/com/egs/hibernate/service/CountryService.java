package com.egs.hibernate.service;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.response.CountryCodeResponse;
import com.egs.hibernate.response.CountryResponse;
import com.egs.hibernate.response.ResponseCountry;

import java.util.List;

public interface CountryService {

    void storeAllCountries();

    List<ResponseCountry> getCountryByUserCount();

//    ResponseCountry getCountryCodeByDisplayName(Country country);
}
