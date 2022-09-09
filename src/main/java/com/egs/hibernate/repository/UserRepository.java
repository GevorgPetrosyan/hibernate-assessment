package com.egs.hibernate.repository;

import com.egs.hibernate.entity.User;
import com.egs.hibernate.response.projection.UserProjection;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = "select * from users u order by u.id desc fetch first row ONLY")
    Optional<User> findLastUser();

    @Query(nativeQuery = true, value =
            "select u.first_name as firstName,\n" +
                    "       u.last_name  as lastName,\n" +
                    "       u.birthdate  as birthdate,\n" +
                    "       u.username   as username\n" +
                    "from users u")
    List<UserProjection> findAllUsers(Pageable pageable);

    @Query(nativeQuery = true,value = "select distinct count(u.\"id\") from users u\n" +
            "left join address a on u.id = a.user_id\n" +
            "and a.country_id = (select id from country c where c.country_code =:code)\n" +
            "where a.country_id is not null;\n")
    Integer findUsersCountByCountryCode(@Param("code")String code);

}
