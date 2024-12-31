package com.example.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "wind_farm")
public class WindFarm implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 100)
    private String location;

    @OneToMany(mappedBy = "windFarm", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<WindTurbine> windTurbines;
    
}
