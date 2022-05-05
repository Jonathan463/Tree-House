package com.example.treehouse.repository;

import com.example.treehouse.model.Address;
import com.example.treehouse.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByPerson_Id(Long id);
    Optional<Address> findFirstByPerson(Person person);
}
