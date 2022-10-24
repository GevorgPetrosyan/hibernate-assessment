package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.exceptions.CountryNotFoundException;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
//@CacheConfig(cacheNames = "countryCode")
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    private final CacheManager cacheManager;

    // cache show method,  by debug and sout names
    public void getManualData(){
        Cache name = cacheManager.getCache("countryId");
        System.out.println(name);
        Cache name2 = cacheManager.getCache("countryCode");
        System.out.println(name2);
        Cache name3 = cacheManager.getCache("country");
        System.out.println(name3);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void storeAllCountries() {
        if (countryRepository.count() == 0) {
            Arrays.stream(CountryCode.values())
                    .map(it -> Country.builder().countryCode(it).displayName(it.getName()).build())
                    .forEach(countryRepository::save);
        }
    }

    //todo delete
    // method for 2th cache test
    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "countryId")
    public Optional<Country> getCountryById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "countryCode")
    public CountryCode getCountryCodeByDisplayName(String displayName) {
        log.info("Get Country Code method start work!");
        CountryCode countryCode = countryRepository.findCountryCodeByDisplayName(displayName);
        if (countryCode == null) {
            log.error("Country with displayName: {} can't be gotten.", displayName);
            throw new CountryNotFoundException("CountryCode for " + displayName + " not found.");
        }
        return countryCode;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "countryCode")
    public List<CountryCode> getCountryCodes() {
        log.info("GetCountryCodes method start work!");
        return countryRepository.getAllCountryCodes();
    }

    @Override
    @CachePut(value = "countryCode")
    public List<CountryCode> updateCountryCodesInCache() {
        log.info("UpdateCountryCodesInCache method start work!");
        return countryRepository.getAllCountryCodes();
    }

    // todo delete
    // method for query level cache test
    @Override
    @Cacheable(cacheNames = "country")
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}

