package com.example.personData.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String contactDetail;

    @ManyToOne
    private Person person;

}
