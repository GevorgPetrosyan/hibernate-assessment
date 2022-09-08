package com.egs.hibernate.assigment.core.service.impl;

import com.egs.hibernate.assigment.core.entity.Country;
import com.egs.hibernate.assigment.core.repository.CountryRepository;
import com.egs.hibernate.assigment.core.service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void storeAllCountries() {
        if (countryRepository.count() == 0) {
            Arrays.stream(CountryCode.values())
                    .map(it -> Country.builder().countryCode(it).displayName(it.getName()).build())
                    .forEach(countryRepository::save);
        }
    }
}
