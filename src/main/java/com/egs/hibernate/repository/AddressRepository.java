package com.egs.hibernate.repository;

import com.egs.hibernate.entity.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
  List<Address> findAllByUserId(Long userId);
}
