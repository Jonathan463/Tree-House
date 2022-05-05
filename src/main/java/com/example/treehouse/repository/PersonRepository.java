package com.example.treehouse.repository;

import com.example.treehouse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findPersonByPassword(String password);
    Optional<Person> findPersonByUserName(String username);
    Optional<Person> findPersonByUserNameAndPassword(String username,String password);

    Optional<Person> findByUserName(String userName);
    Optional<Person> findByEmail(String email);
    Optional<Person> findByResetPasswordToken(String token);
}
