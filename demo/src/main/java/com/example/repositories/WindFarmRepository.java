package com.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.WindFarm;

@Repository
public interface WindFarmRepository extends JpaRepository<WindFarm, Long> {}
