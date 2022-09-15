package com.egs.hibernate.assigment.core.repository;

import com.egs.hibernate.assigment.core.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
