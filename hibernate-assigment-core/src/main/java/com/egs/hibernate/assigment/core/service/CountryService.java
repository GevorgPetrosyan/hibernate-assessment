package com.egs.hibernate.assigment.core.service;

import com.egs.hibernate.assigment.data.transfer.response.CountryCodeResponse;

import java.util.List;

public interface CountryService {

    void storeAllCountries();

    List<String> getAllByUsersCount(Long validateCount);

    List<CountryCodeResponse> getAllAvailableByCountryCodes();

    CountryCodeResponse getByDisplayName(String displayName);
}
