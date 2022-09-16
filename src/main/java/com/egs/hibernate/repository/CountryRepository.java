package com.egs.hibernate.repository;

import com.egs.hibernate.entity.Country;
import com.egs.hibernate.model.CountryResponseModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    @Query("select c.countryCode as countryCode from User u join u.addresses a join a.country c group by c having count (u) > 10000")
    List<CountryResponseModel> getCountriesByUser();

}
