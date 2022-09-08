package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findFirstByOrderByIdDesc();
}
