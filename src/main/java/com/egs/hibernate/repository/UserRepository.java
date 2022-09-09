package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByOrderByIdDescCreatedDesc();
}
