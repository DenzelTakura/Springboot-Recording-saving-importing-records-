package com.example.personData.model.Dtos;

import lombok.Data;

@Data
public class PersonDto {
    private Long id;
    private String idNumber;
    private String firstName;
    private String lastName;
}
