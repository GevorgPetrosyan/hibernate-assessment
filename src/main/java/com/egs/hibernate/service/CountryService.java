package com.egs.hibernate.service;

import java.util.List;

public interface CountryService {
    void storeAllCountries();

    List<String> getCountryByUserCount();
}
