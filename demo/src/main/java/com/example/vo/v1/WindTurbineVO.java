package com.example.vo.v1;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.*;

@Data
public class WindTurbineVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String idCode;
    private String model;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long windFarmId;
    private List<AudioVO> audios;

    public WindTurbineVO() {}
}
