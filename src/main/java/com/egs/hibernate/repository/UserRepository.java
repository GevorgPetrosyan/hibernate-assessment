package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.model.UserFullResponseModel;
import com.egs.hibernate.model.UserResponseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findFirstByOrderByCreatedDesc();

    @Query(value = "select users.username from users order by id desc limit 1", nativeQuery = true)
    String getUserNameFindFirstByOrderByCreatedDesc();

    @Query(value="select new com.egs.hibernate.model.UserResponseModel(u.username,u.firstName,u.lastName,u.birthdate)" +
            "from users u",
            countQuery = "select count (u.id) from users u")
    Page<UserResponseModel> getUserResponseModel(Pageable paging);

    @Query(value = "select new com.egs.hibernate.model.UserFullResponseModel(" +
            "u.username," +
            "u.firstName," +
            "u.lastName," +
            "u.birthdate," +
            "a.street," +
            " a.addressLine1," +
            " a.addressLine2," +
            " a.city," +
            " a.postalCode," +
            " c.countryCode," +
            " c.displayName," +
            " pn.phoneNumber) from users as u " +
            "inner join address as a on u.id = a.user.id left join country as c on c.id = a.country.id inner join phone_number as pn on u.id = pn.user.id ")
    Slice<UserFullResponseModel> getUserFullResponseModel(Pageable paging);
}
