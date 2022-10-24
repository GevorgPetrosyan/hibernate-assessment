package com.egs.hibernate.service;

import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public interface CountryService {

    void storeAllCountries();

    CountryCode getCountryCodeByDisplayName(String displayName);

    List<CountryCode> getCountryCodes();

    List<CountryCode> updateCountryCodesInCache();
}
