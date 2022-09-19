package com.egs.hibernate.service;

import com.egs.hibernate.model.CountryResponseWith10K;

import java.util.List;

public interface CountryService {
    void storeAllCountries();

    List<CountryResponseWith10K> getCountry();
}

