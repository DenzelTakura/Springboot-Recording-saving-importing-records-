package com.example.personData.serviceimpl;

import com.example.personData.model.Dtos.PersonDto;
import com.example.personData.model.Person;
import com.example.personData.repository.PersonRepo;
import com.example.personData.service.PersonService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepo personRepo;

    public PersonServiceImpl(PersonRepo personRepo) {
        this.personRepo = personRepo;
    }

    @Override
    public Person addPerson(Person person) {
        System.out.println("New person Added : "+person.getFirstName()+ " "+person.getLastName());
        return personRepo.save(person);
    }

    @Override
    public Person findById(Long id) {
        return personRepo.getReferenceById(id);
    }

    @Override
    public Person findByIdNumber(String IdNumber) {
        return personRepo.findPersonByIdNumber(IdNumber);
    }

    @Override
    public void deleteById(Long id) {
        personRepo.deleteById(id);
    }


    @Override
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public void getPeopleFromSystem() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.32.33:8081/person/all";
        ResponseEntity<List<PersonDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PersonDto>>() {
                }
        );

        List<PersonDto> personDtos = response.getBody();
        for(PersonDto personDto:personDtos){
            Person person = new Person();
            person.setFirstName(personDto.getFirstName());
            person.setLastName(personDto.getLastName());
            person.setIdNumber(personDto.getIdNumber());
            personRepo.save(person);
            System.out.println("collected info for : "+person.getFirstName() +" "+person.getLastName());
        }
    }
}
