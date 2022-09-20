package com.egs.hibernate.repository;

import com.egs.hibernate.entity.BaseUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface UserRepository extends JpaRepository<BaseUser, Long> {


    @Query(nativeQuery = true, value = "select * from users u order by u.id desc fetch first row ONLY")
    Optional<BaseUser> findLastUser();

}
