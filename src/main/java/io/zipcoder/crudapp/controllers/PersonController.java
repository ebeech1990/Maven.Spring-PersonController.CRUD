package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @PostMapping(path = "/people")
    public Person createPerson(@RequestBody Person p){
        return personRepository.save(p);
    }

    @GetMapping(path = "/people/{id}")
    public Person getPerson(@PathVariable Long id){
        return personRepository.findOne(id);
    }

    @GetMapping(path = "/people")
    public List<Person> getPersonList(){
        return (List<Person>) personRepository.findAll();
    }

    @PutMapping(path = "/people/{id}")
    public Person updatePerson(@PathVariable Long id, @RequestBody Person p){
        Person retrieved = getPerson(id);
        retrieved.setFirstName(p.getFirstName());
        retrieved.setLastName(p.getLastName());
        return personRepository.save(retrieved);
    }

    @DeleteMapping(path = "/people/{id}")
    public void deletePerson(@PathVariable Long id){
        personRepository.delete(id);
    }


}
