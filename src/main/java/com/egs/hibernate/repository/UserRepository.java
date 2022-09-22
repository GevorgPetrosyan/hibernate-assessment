package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByOrderByCreatedDesc();

    Optional<User> findFirstByOrderByIdDesc();

    @Query("select u from users u left join u.addresses join u.phoneNumbers")
    @Override
    Page<User> findAll(Pageable pageable);
}
