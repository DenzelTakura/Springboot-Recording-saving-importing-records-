package com.example.personData.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String idNumber;
    private Date  DOB;
    @Enumerated(EnumType.STRING)
    private Gender gender;
}
