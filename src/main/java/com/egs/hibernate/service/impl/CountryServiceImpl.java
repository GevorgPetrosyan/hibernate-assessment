package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.service.CountryService;
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
    @Transactional(propagation = Propagation.REQUIRED)
    public void storeAllCountries() {
        if (countryRepository.count() == 0) {
            Arrays.stream(CountryCode.values())
                    .map(it -> Country.builder().countryCode(it.getAlpha2()).displayName(it.getName()).build())
                    .forEach(countryRepository::save);
        }
    }
}
