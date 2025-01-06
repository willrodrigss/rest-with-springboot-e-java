package com.example.mapper;
import org.springframework.stereotype.Service;

import com.example.model.WindFarm;
import com.example.vo.v1.WindFarmVO;

@Service
public class WindFarmMapper {
    public WindFarmVO convertEntityToVO(WindFarm windFarm){
        WindFarmVO vo = new WindFarmVO();

        vo.setId(windFarm.getId());
        vo.setName(windFarm.getName());
        vo.setLocation(windFarm.getLocation());

        return vo;
    }

    public WindFarm convertVOtoEntity(WindFarmVO windFarm){
        WindFarm entity = new WindFarm();

        entity.setId(windFarm.getId());
        entity.setName(windFarm.getName());
        entity.setLocation(windFarm.getLocation());

        return entity;
    }
}
