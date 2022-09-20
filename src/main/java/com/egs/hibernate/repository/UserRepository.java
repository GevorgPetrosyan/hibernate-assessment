package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.response.CountryCodeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findFirstByOrderByIdDescCreatedDesc();

    @Query(value = "SELECT u FROM users u JOIN FETCH u.phoneNumbers ph JOIN FETCH u.addresses a JOIN FETCH a.country c",
            countQuery = "SELECT COUNT(id) FROM users ")
    Page<User> findAll(Pageable pageable);

    @Query("SELECT c.countryCode AS countryCode, COUNT(u.id) AS count " +
            "FROM address a JOIN a.user u JOIN a.country c GROUP BY c")
    List<CountryCodeResponse> findUsersByCountryCode();

}
