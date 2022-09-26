package com.egs.hibernate.service;

import java.util.List;

public interface CountryService {
    void storeAllCountries();

    String getCountryCodeByDisplayName(String displayName);

    List<String> getCountryCodes();
}
