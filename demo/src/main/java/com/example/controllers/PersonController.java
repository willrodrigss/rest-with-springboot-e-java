package com.example.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.example.model.Person;
import com.example.services.PersonServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/person")
public class PersonController {    
    @Autowired
    private PersonServices service;
    //private PersonServices service = new PersonServices();

    @RequestMapping(method= RequestMethod.GET, produces= org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() {
        return service.findAll();
    }

    @RequestMapping(value= "/{id}", method= RequestMethod.GET, produces= org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") String id){
        return service.findById(id);
    }
}
