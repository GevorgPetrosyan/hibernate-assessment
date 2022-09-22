package com.egs.hibernate.service;

import java.util.List;

public interface CountryService {
    void storeAllCountries();

    List<String> getAllCountryCodes();

    String getCountryCode(final String displayName);
}
