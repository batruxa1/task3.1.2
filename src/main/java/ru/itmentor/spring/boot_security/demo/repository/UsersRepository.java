package ru.itmentor.spring.boot_security.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.User;


@Repository
public interface UsersRepository extends JpaRepository <User, Integer> {
    @Query("SELECT u FROM User u WHERE u.userName = :username")
    User findByUserName(String username);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = :email")
    User getUserByEmail(@Param("email") String email);
}
