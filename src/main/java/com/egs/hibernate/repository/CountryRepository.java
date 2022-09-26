package com.egs.hibernate.repository;

import com.egs.hibernate.entity.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    Country findCountryByDisplayName(String displayName);
    List<Country> findAll();
}
