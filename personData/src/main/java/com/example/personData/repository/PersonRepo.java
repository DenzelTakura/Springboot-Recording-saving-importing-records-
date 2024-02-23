package com.example.personData.repository;

import com.example.personData.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {
    Person findPersonByIdNumber(String IdNumber);
    @Override
    Person save(Person person);

    @Override
    List<Person> findAll();

    void deleteById(Long Id);
}
