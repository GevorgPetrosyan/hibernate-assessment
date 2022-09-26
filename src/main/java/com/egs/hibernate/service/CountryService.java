package com.egs.hibernate.service;

import com.egs.hibernate.response.CountriesWithTenKUsersResponse;
import com.egs.hibernate.response.CountryResponse;
import com.neovisionaries.i18n.CountryCode;

import java.util.List;

public interface CountryService {
    void storeAllCountries();

    CountriesWithTenKUsersResponse findCountriesWithTenKUsers();

    CountryResponse getCountryCodeByDisplayName(String displayName);

    List<CountryCode> getAllCountries();

}
