package com.egs.hibernate.service;

import com.egs.hibernate.model.CountryResponseModel;

import java.util.List;

public interface CountryService {
    void storeAllCountries();

    List<CountryResponseModel> getCountryByUserCount();
}
