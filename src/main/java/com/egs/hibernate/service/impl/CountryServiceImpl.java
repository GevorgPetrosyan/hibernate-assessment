package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.exceptions.CountryNotFoundException;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
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

    @Override
    @Transactional(readOnly = true)
    public CountryCode getCountryCodeByDisplayName(String displayName) {
        log.info("GetCountryCod method start work!");
        Country country = countryRepository.findCountryByDisplayName(displayName);
        if (country == null) {
            log.error("Country with displayName: {} can't be gotten.", displayName);
            throw new CountryNotFoundException("CountryCode for " + displayName + " not found.");
        }
        return country.getCountryCode();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CountryCode> getCountryCodes() {
        log.info("GetCountryCodes method start work!");
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                .map(country -> country.getCountryCode())
                .collect(Collectors.toList());
    }
}
