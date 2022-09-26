package com.egs.hibernate.service;

public interface CountryService {
    void storeAllCountries();

    String getCountryCodeByDisplayName(String displayName);
}
