package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByOrderByIdDesc();

    @Query(nativeQuery = true,value = "select * from users")
    List<User> findAllUsers(Pageable pageable);
}
