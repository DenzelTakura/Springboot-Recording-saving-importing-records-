package com.example.personData.service;

import com.example.personData.model.Person;

import java.util.List;

public interface PersonService {
    Person addPerson(Person person);
    Person findById(Long id);
    Person findByIdNumber(String idNumber);
    void deleteById(Long id);

    List<Person> findAll();
    void getPeopleFromSystem();
}
