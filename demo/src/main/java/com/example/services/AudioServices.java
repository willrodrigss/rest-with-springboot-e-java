package com.example.services;

import java.util.logging.Logger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.exceptions.ResourceNotFoundException;
import com.example.mapper.DozerMapper;
import com.example.vo.v1.AudioVO;
import com.example.model.WindTurbine;
import com.example.model.Audio;
import com.example.repositories.AudioRepository;
import com.example.repositories.WindTurbineRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AudioServices {

    private Logger logger = Logger.getLogger(AudioServices.class.getName());

    @Autowired
    AudioRepository repository;

    @Autowired
    WindTurbineRepository windTurbineRepository;

    public List<AudioVO> findAll() {
        logger.info("Finding all audios!");
        List<AudioVO> vos = DozerMapper.parseListObjects(repository.findAll(), AudioVO.class);

        for (int i = 0; i < vos.size(); i++) {
            AudioVO vo = vos.get(i);
            var entity = repository.findAll().get(i);
            setWindTurbineId(vo, entity);
        }

        return vos;
    }

    @Transactional
    public AudioVO findById(Long id) {
        logger.info("Finding one audio!");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        var vo = DozerMapper.parseObject(entity, AudioVO.class);
        setWindTurbineId(vo, entity);

        return vo;
    }

    public AudioVO create(AudioVO audio) {
        logger.info("Creating one audio!");

        var entity = DozerMapper.parseObject(audio, Audio.class);

        WindTurbine windTurbine = windTurbineRepository.findById(audio.getWindTurbineId())
            .orElseThrow(() -> new ResourceNotFoundException("WindTurbine not found for ID: " + audio.getWindTurbineId()));

        entity.setWindTurbine(windTurbine);
        var savedEntity = repository.save(entity);

        var vo = DozerMapper.parseObject(savedEntity, AudioVO.class);
        setWindTurbineId(vo, savedEntity);

        return vo;
    }

    public AudioVO update(AudioVO audio) {
        logger.info("Updating one audio!");

        var entity = repository.findById(audio.getId())
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setPath(audio.getPath());

        var savedEntity = repository.save(entity);
        var vo = DozerMapper.parseObject(savedEntity, AudioVO.class);
        setWindTurbineId(vo, savedEntity);

        return vo;
    }

    public void delete(Long id) {
        logger.info("Deleting one audio!");

        var entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }

    private void setWindTurbineId(AudioVO vo, Audio entity) {
        if (entity.getWindTurbine() != null) {
            vo.setWindTurbineId(entity.getWindTurbine().getId());
        }
    }
}
