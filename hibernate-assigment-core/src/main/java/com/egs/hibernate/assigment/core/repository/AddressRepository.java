package com.egs.hibernate.assigment.core.repository;

import com.egs.hibernate.assigment.core.entity.Address;
import com.egs.hibernate.assigment.core.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    long countDistinctByUserIsNotNullAndCountry(Country country);
}
