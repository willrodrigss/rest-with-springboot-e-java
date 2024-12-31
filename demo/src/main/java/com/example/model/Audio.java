package com.example.model;

import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.*;

@Data
@Entity
@Table(name = "audio")
public class Audio implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String path;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wind_turbine_id", nullable = false)
    private WindTurbine windTurbine;
}
