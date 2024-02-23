package com.example.personData.controller;

import com.example.personData.model.Contact;
import com.example.personData.service.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/add")
    public Contact AddContact(@RequestBody Contact contact){
        return contactService.add(contact);
    }

    @GetMapping("/import")
    public void getContactsExternally(){
        contactService.getContactsFromSystem();
    }

    @GetMapping("/all")
    public List<Contact> findAll(){
        return contactService.findAll();
    }
}
