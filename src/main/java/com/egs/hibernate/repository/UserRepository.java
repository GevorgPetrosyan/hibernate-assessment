package com.egs.hibernate.repository;

import com.egs.hibernate.dto.UsersCountDTO;
import com.egs.hibernate.entity.User;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findFirstByOrderByIdDesc();

    List<User> findAll(Pageable pageable);

    @Query(value = "SELECT new com.egs.hibernate.dto.UsersCountDTO(c.countryCode , count(DISTINCT u.id)) " +
            "FROM users u " +
            "JOIN address a ON u.id = a.user.id " +
            "JOIN country c ON c.id = a.country.id " +
            "GROUP BY c.countryCode " +
            "ORDER BY COUNT(c.countryCode) DESC")
    List<UsersCountDTO> usersCountByCountryCode();

    @Query(value = "SELECT c.countryCode " +
            "FROM users u " +
            "JOIN address a ON u.id = a.user.id " +
            "JOIN country c ON c.id = a.country.id " +
            "GROUP BY c.countryCode " +
            "HAVING count(u.id) > 10000")
    List<CountryCode> usersCluster();

}
