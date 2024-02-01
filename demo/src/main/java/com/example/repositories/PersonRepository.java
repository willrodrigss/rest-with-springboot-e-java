package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {}
