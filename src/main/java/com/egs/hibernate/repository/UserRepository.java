package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "select username from users where id IN (select MAX(u1.id) FROM users AS u1)")
    Optional<String> findUserByMaxID();

    List<User> findByOrderByUsernameAsc(Pageable paging);
    List<User> findByOrderByFirstNameAsc(Pageable paging);
    List<User> findByOrderByLastNameAsc(Pageable paging);
    List<User> findByOrderByBirthdateAsc(Pageable paging);
}
