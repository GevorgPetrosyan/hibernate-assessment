package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.model.UserCountryResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findFirstByOrderByCreatedDesc();

    @Query(value = "select users.username from users order by id desc limit 1", nativeQuery = true)
    String getUserNameFindFirstByOrderByIdDesc();

    @Query(value="select u.username as username, u.firstName as firstName, u.lastName as lastName, u.birthdate as birthdate from users u",
            countQuery = "select count (u.id) from users u")
    Page<UserResponseModel> getUserResponseModel(Pageable paging);

    @Query(value = "select distinct u from users u" +
            " join fetch u.addresses a join fetch u.phoneNumbers pn join fetch a.country")
    Slice<User> getUserFullResponseModel(Pageable paging);

    @Query(value = "select c.countryCode as countryCode, count(u) as count from users u" +
            " join u.addresses a join a.country c group by c")
    List<UserCountryResponseModel> getCountOfUsersByCountry();
}
