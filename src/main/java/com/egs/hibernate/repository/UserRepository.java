package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  @Query(value = "select username from users where id IN (select MAX(u1.id) FROM users AS u1)", nativeQuery = true)
  Optional<String> findUsernameByMaxID();
}
