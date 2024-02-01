package com.example.services;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.model.Person;

@Service
public class PersonServices {
    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll(){

        logger.info("Finding all people!");
        List<Person> persons = new ArrayList<>();

        //Onde é invocada a base de dados

        for(int i=0; i<8; i++){
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

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
    
    public Person create(Person person){
        logger.info("Creating one person!");
        return person;
    }

    public Person update(Person person){
        logger.info("Updating one person!");
        return person;
    }
    
    //mock para simulação
    private Person mockPerson(int i) {
        
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name " + i);
        person.setLastName("Last name" + i);
        person.setAddress("Some addres in Brasil " + i);
        person.setGender("male");

        return person;
    }
}
