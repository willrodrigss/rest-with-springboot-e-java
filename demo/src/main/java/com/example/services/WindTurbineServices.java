package com.example.services;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.DozerMapper;
import com.example.vo.v1.WindTurbineVO;
import com.example.model.WindFarm;
import com.example.model.WindTurbine;
import com.example.repositories.WindTurbineRepository;
import com.example.repositories.WindFarmRepository;


@Service
public class WindTurbineServices {

    private Logger logger = Logger.getLogger(WindTurbineServices.class.getName());

    @Autowired
    WindTurbineRepository repository;

    @Autowired
    WindFarmRepository windFarmRepository;

    public List<WindTurbineVO> findAll(){
        logger.info("Finding all wind turbines!");
        List<WindTurbineVO> vos = DozerMapper.parseListObjects(repository.findAll(), WindTurbineVO.class);

        for (int i = 0; i < vos.size(); i++) {
            WindTurbineVO vo = vos.get(i);
            var entity = repository.findAll().get(i);

            setWindFarmId(vo, entity);
        }

        return vos;
    }

    public WindTurbineVO findById(Long id){
        logger.info("Finding one wind turbine!");

        //Onde é invocada a base de dados

        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        var vo = DozerMapper.parseObject(entity, WindTurbineVO.class);
        setWindFarmId(vo, entity);

        return vo;
    }
    
    public WindTurbineVO create(WindTurbineVO windTurbine){
        logger.info("Creating one wind turbine!");
        var entity = DozerMapper.parseObject(windTurbine, WindTurbine.class);

        WindFarm windFarm = windFarmRepository.findById(windTurbine.getWindFarmId())
            .orElseThrow(() -> new ResourceNotFoundException("WindFarm not found for ID: " + windTurbine.getWindFarmId()));

        entity.setWindFarm(windFarm);
        var savedTurbine = repository.save(entity);
        var vo = DozerMapper.parseObject(savedTurbine, WindTurbineVO.class);

        setWindFarmId(vo, entity);

        return vo;
    }

    public WindTurbineVO update(WindTurbineVO windTurbine){
        logger.info("Updating one wind turbine!");
        
        var entity = repository.findById(windTurbine.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setId(windTurbine.getId()); // Caso o ID seja necessário
        entity.setIdCode(windTurbine.getIdCode());
        entity.setModel(windTurbine.getModel());
        entity.setLatitude(windTurbine.getLatitude());
        entity.setLongitude(windTurbine.getLongitude());
        
        var vo = DozerMapper.parseObject(repository.save(entity), WindTurbineVO.class);

        setWindFarmId(vo, entity);

        return vo;
    }

    public void delete(Long id){
        logger.info("Deleting one wind turbine!");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
            
        repository.delete(entity);
    }

    private void setWindFarmId(WindTurbineVO vo, WindTurbine entity) {
        vo.setWindFarmId(entity.getWindFarm().getId());
    }
    
}
