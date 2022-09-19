package com.egs.hibernate.service;

import com.egs.hibernate.response.CountriesWithTenKUsersResponse;

public interface CountryService {
    void storeAllCountries();

    CountriesWithTenKUsersResponse findCountriesWithTenKUsers();
}
