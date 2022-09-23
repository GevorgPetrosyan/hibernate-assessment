package com.egs.hibernate.repository;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.response.ResponseCountry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    @Query(value = "select c.countryCode as countryCode from users u " +
            "join u.addresses a join a.country c group by c having count(u) > 1")
    List<ResponseCountry> findCountriesByUsersCount();

}
