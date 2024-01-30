package com.example.services;

import java.util.concurrent.atomic.AtomicLong;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.example.model.Person;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){
        logger.info("Finding one person!");

        //Onde é invocada a base de dados

        //mock para simulação
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("William");
        person.setLastName("Silva");
        person.setAddress("Cataguases - MG");
        person.setGender("male");

        return person;
    }
}
