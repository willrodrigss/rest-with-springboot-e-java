package com.example.services;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.DozerMapper;
import com.example.vo.v1.WindFarmVO;
import com.example.model.WindFarm;
import com.example.repositories.WindFarmRepository;
import org.springframework.transaction.annotation.Transactional;


@Service
public class WindFarmServices {

    private Logger logger = Logger.getLogger(WindFarmServices.class.getName());

    @Autowired
    WindFarmRepository repository;

    public List<WindFarmVO> findAll(){
        logger.info("Finding all wind farms!");
        return DozerMapper.parseListObjects(repository.findAll(), WindFarmVO.class);
    }

    @Transactional
    public WindFarmVO findById(Long id){
        logger.info("Finding one wind farm!");

        //Onde é invocada a base de dados

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        
        return DozerMapper.parseObject(entity, WindFarmVO.class);
    }
    
    public WindFarmVO create(WindFarmVO windFarm){
        logger.info("Creating one wind farm!");
        var entity = DozerMapper.parseObject(windFarm, WindFarm.class);
        var vo = DozerMapper.parseObject(repository.save(entity), WindFarmVO.class);
        return vo;
    }

    public WindFarmVO update(WindFarmVO windFarm){
        logger.info("Updating one wind farm!");
        
        var entity = repository.findById(windFarm.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setId(windFarm.getId());
        entity.setName(windFarm.getName()); 
        entity.setLocation(windFarm.getLocation());
        
        var vo = DozerMapper.parseObject(repository.save(entity), WindFarmVO.class);
        return vo;
    }

    public void delete(Long id){
        logger.info("Deleting one wind farm!");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
            
        repository.delete(entity);
    }
    
}
