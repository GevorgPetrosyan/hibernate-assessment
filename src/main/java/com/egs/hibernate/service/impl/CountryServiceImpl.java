package com.egs.hibernate.service.impl;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.model.CountryResponseWith10K;
import com.egs.hibernate.repository.CountryRepository;
import com.egs.hibernate.service.CountryService;
import com.neovisionaries.i18n.CountryCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void storeAllCountries() {
        if (countryRepository.count() == 0) {
            Arrays.stream(CountryCode.values())
                    .map(it -> Country.builder().countryCode(it).displayName(it.getName()).build())
                    .forEach(countryRepository::save);
        }
    }

    @Override
    public List<CountryResponseWith10K> getCountry() {
        return entityManager.createQuery("SELECT new com.egs.hibernate.model.CountryResponseWith10K(c.countryCode) " + "FROM users u  " +
                "JOIN u.addresses a " +
                "JOIN a.country c " +
                "GROUP BY c HAVING count(u) > 7600",
                CountryResponseWith10K.class).getResultList();
    }

}
