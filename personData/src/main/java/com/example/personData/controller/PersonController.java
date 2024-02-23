package com.example.personData.controller;

import com.example.personData.model.Person;
import com.example.personData.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/add")
    public Person addPerson(@RequestBody Person person){
        return personService.addPerson(person);
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        personService.deleteById(id);
    }


    @GetMapping("/all")
    public List<Person> findAll() {
        return personService.findAll();
    }
    @GetMapping("/from-app")
    public void findFromApp() {
        personService.getPeopleFromSystem();
    }
}
