package com.egs.hibernate.service;

import com.egs.hibernate.entity.Country;
import com.neovisionaries.i18n.CountryCode;

import java.util.List;
import java.util.Optional;

public interface CountryService {

    void getManualData();

    void storeAllCountries();

    Optional<Country> getCountryById(Long id);

    CountryCode getCountryCodeByDisplayName(String displayName);

    List<CountryCode> getCountryCodes();

    List<CountryCode> updateCountryCodesInCache();

    List<Country> getAllCountries();
}
