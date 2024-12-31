package com.example.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.vo.v1.WindTurbineVO;
import com.example.services.WindTurbineServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/wind-turbine")
public class WindTurbineController {    
    @Autowired
    private WindTurbineServices service;
    //private WindTurbineServices service = new WindTurbineServices();

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<WindTurbineVO> findAll() {
        return service.findAll();
    }

    @GetMapping(value= "/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
    public WindTurbineVO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public WindTurbineVO create(@RequestBody WindTurbineVO windTurbine){
        return service.create(windTurbine);
    }

    @PutMapping(consumes= MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    public WindTurbineVO update(@RequestBody WindTurbineVO windTurbine){
        return service.update(windTurbine);
    }

    @DeleteMapping(value= "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
