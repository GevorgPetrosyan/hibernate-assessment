package com.egs.hibernate.repository;

import com.egs.hibernate.entity.Country;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("select c.countryCode from users u left join address a on u.id = a.user.id left join country c on c.id = a.country.id " +
            "group by c.countryCode having count(u) > 90 order by c.countryCode")
    List<CountryCode> getCountriesByUserCount();
}