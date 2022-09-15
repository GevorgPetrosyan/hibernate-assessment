package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.rest.model.country.CountryCodeWithManyUsersResponse;
import com.egs.hibernate.rest.model.user.UserCountWithCountryCodeResponse;
import com.egs.hibernate.service.UserSearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserSearchRepository {

    @Query(value = "select username from users where id IN (select MAX(u1.id) FROM users AS u1)", nativeQuery = true)
    Optional<String> findFirstUsernameByMaxId();

    @Query(value = "SELECT new com.egs.hibernate.rest.model.user.UserCountWithCountryCodeResponse(c.countryCode , count(u)) " +
            "FROM users AS u " +
            "JOIN address AS a ON u.id = a.user.id " +
            "JOIN country AS c ON c.id = a.country.id " +
            "WHERE LENGTH(c.countryCode) < 3 " +
            "GROUP BY c.countryCode " +
            "ORDER BY c.countryCode ")
    List<UserCountWithCountryCodeResponse> getUsersCountWithCountryCode();

    @Query(value = "SELECT new com.egs.hibernate.rest.model.country.CountryCodeWithManyUsersResponse(c.countryCode)" +
            "FROM users AS u " +
            "JOIN address AS a ON u.id = a.user.id " +
            "JOIN country AS c ON c.id = a.country.id " +
            "GROUP BY c.countryCode " +
            "HAVING count(u) > 10000" +
            "ORDER BY c.countryCode ")
    List<CountryCodeWithManyUsersResponse> getCountryWithManyUsers();

}
