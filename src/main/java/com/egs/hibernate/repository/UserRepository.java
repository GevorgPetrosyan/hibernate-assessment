package com.egs.hibernate.repository;

import com.egs.hibernate.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<BaseUser, Long> {

    @Query(nativeQuery = true, value = "select * from users u order by u.id desc fetch first row ONLY")
    Optional<BaseUser> findLastUser();

    @Query(nativeQuery = true, value = "select distinct count(u.\"id\") from users u\n" +
            "left join address a on u.id = a.user_id\n" +
            "and a.country_id = (select id from country c where c.country_code =:code)\n" +
            "where a.country_id is not null;\n")
    Integer findUsersCountByCountryCode(@Param("code") String code);

}
