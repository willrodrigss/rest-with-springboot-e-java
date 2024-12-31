package com.example.vo.v1;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;

@Data
public class AudioVO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String path;
    private Long windTurbineId;

    public AudioVO() {}
}
