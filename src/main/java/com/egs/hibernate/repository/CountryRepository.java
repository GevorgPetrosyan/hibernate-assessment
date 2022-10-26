package com.egs.hibernate.repository;

import com.egs.hibernate.entity.Country;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    @Query(value = "SELECT countryCode FROM country WHERE displayName = ?1")
    CountryCode findCountryCodeByDisplayName(String displayName);

    @Query(value = "SELECT countryCode FROM country")
    List<CountryCode> getAllCountryCodes();

}
