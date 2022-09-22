package com.egs.hibernate.service;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public interface CountryService {
    void storeAllCountries();

    List<CountryCode> getCountryByUserCount();
}
