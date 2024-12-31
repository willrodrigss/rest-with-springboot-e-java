package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.WindTurbine;

@Repository
public interface WindTurbineRepository extends JpaRepository<WindTurbine, Long> {}
