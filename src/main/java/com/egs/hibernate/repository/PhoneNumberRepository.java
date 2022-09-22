package com.egs.hibernate.repository;

import com.egs.hibernate.entity.Address;
import com.egs.hibernate.entity.PhoneNumber;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
  List<PhoneNumber> findAllByUserId(Long userId);
}
