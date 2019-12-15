package com.furrychickens.natlib.repository;

import com.furrychickens.natlib.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
