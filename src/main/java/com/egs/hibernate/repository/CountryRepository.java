package com.egs.hibernate.repository;

import com.egs.hibernate.entity.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    @Query("SELECT c.countryCode " +
            "FROM country AS c " +
            "WHERE LENGTH(c.countryCode) < 3 " +
            "ORDER BY c.countryCode ASC ")
    List<String> getAllCountryCode();

    @Query("SELECT c.countryCode " +
            "FROM country as c " +
            "WHERE c.displayName = ?1")
    String getCountryCode(final String displayName);
}
