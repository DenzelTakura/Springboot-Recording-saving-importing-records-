package com.example.personData.service;

import com.example.personData.model.Contact;

import java.util.List;


public interface ContactService {
    void getContactsFromSystem();
    Contact add(Contact contact);
    Contact contactCheck(Contact contact, Contact[] contacts);
    List<Contact> findAll();
    boolean contactDetailUnique(String contactDetail);


}
