package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.model.UserCountryResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByOrderByCreatedDesc();

    @Query(value = "SELECT username FROM users ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String getUsernameByOrderByIdDesc();

    @Query(value = "select u.username as username, u.firstName as firstName, u.lastName as lastName, u.birthdate as birthdate from User u")
    Page<UserResponseModel> getAll(Pageable pageable);

    @Query(value = "select distinct u from User u join fetch u.addresses a join fetch u.phoneNumbers pn join fetch a.country",
            countQuery = "select count (u.id) from User u")
    Page<User> getAllUsers(Pageable pageable);

    @Query("select c.countryCode as countryCode, count(u) as count " +
            "from User u join u.addresses a join a.country c group by c")
    List<UserCountryResponseModel> getCountOfUsersByCountry();
}
