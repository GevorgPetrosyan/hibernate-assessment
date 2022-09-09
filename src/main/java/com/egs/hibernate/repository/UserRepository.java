package com.egs.hibernate.repository;

import com.egs.hibernate.dto.UserDto;
import com.egs.hibernate.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findFirstByOrderByIdDesc();

  @Query(nativeQuery = true, value = "select\n" +
      "       users.first_name as firstName,\n" +
      "       users.last_name as lastName,\n" +
      "       users.birthdate as birthdate,\n" +
      "       users.username as username\n" +
      "  from users")
  List<UserDto> findAllUsers(Pageable pageable);
}
