package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.service.UserSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserSearchRepository {

    @Query(value = "select username from users where id IN (select MAX(u1.id) FROM users AS u1)", nativeQuery = true)
    Optional<String> findFirstUsernameByMaxId();

}
