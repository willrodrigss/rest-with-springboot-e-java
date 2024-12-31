package com.example.model;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "wind_turbine")  // Nome da tabela ajustado
public class WindTurbine implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_code", nullable = false, length = 5)
    private String idCode;

    @Column(nullable = false, length = 80)
    private String model;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 10, scale = 6)
    private BigDecimal longitude;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wind_farm_id")
    private WindFarm windFarm;

    @OneToMany(mappedBy = "windTurbine", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Audio> audios;
}
