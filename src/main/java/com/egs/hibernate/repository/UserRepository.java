package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findFirstByOrderByUsernameDesc();
    List<User> findByOrderByUsernameAsc(Pageable paging);
    List<User> findByOrderByFirstNameAsc(Pageable paging);
    List<User> findByOrderByLastNameAsc(Pageable paging);
    List<User> findByOrderByBirthdateAsc(Pageable paging);
}
