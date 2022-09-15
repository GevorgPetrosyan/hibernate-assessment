package com.egs.hibernate.assigment.core.service;

import java.util.List;

public interface CountryService {

    void storeAllCountries();

    List<String> getAllByUsersCount(Long validateCount);
}
