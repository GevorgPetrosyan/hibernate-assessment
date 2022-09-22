package com.egs.hibernate.service;

import com.egs.hibernate.response.ResponseCountry;

import java.util.List;

public interface CountryService {

    void storeAllCountries();

    List<ResponseCountry> getCountryByUserCount();
}
