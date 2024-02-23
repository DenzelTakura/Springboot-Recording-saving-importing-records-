package com.example.personData.model.Dtos;

import com.example.personData.model.Person;
import lombok.Data;

@Data
public class ContactDto {
    private Long id;
    private String contactDetail;
    private Person person;
}
