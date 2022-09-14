package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.model.UserCountryResponseModel;
import com.egs.hibernate.model.UserFullResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
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

    @Query(value = "select new com.egs.hibernate.model.UserResponseModel(u.username, u.firstName, u.lastName, u.birthdate) from User u")
    Page<UserResponseModel> getAll(Pageable pageable);

    @Query(value = "select new com.egs.hibernate.model.UserFullResponseModel(" +
            "u.username, " +
            "u.firstName, " +
            "u.lastName, " +
            "u.birthdate, " +
            "pn.phoneNumber," +
            "a.street, " +
            "a.addressLine1, " +
            "a.addressLine2, " +
            "a.city, " +
            "a.postalCode, " +
            "c.countryCode, " +
            "c.displayName) from User u join phone_number pn on u.id = pn.user.id join address a on u.id = a.user.id left join country c on c.id = a.country.id")
    Slice<UserFullResponseModel> getAllUsers(Pageable pageable);

    @Query("select new com.egs.hibernate.model.UserCountryResponseModel(c.countryCode, count(u)) " +
            "from User u join address as a on u.id = a.user.id join country as c on c.id = a.country.id group by c")
    Slice<UserCountryResponseModel> getCountOfUsersByCountry(Pageable pageable);
}
