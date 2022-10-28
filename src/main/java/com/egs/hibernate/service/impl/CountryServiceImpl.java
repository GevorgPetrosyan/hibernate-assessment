package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.exceptions.CountryNotFoundException;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@CacheConfig(cacheNames = "countryCode")
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
    @Cacheable
    public List<CountryCode> getCountryCodeByDisplayName(String displayName) {
        log.info("Get Country Code method start work!");
        List<CountryCode> countryCodes = countryRepository.findCountryCodeByDisplayName(displayName);
        if (countryCodes.isEmpty()) {
            log.error("Country codes with displayName: {} can't be gotten.", displayName);
            throw new CountryNotFoundException("CountryCode(s) for " + displayName + " not found.");
        }
        return countryCodes;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable
    public List<CountryCode> getCountryCodes() {
        log.info("GetCountryCodes method start work!");
        return countryRepository.getAllCountryCodes();
    }

    @CacheEvict(value = "countryCode", allEntries = true)
    @Scheduled(fixedRateString = "${spring.jpa.properties.caching.countryListTTL}")
    public void emptyCountryCodeCache() {
        log.info("Emptying CountryCodes cache");
    }
}

