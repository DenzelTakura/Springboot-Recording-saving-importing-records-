package com.example.personData.repository;

import com.example.personData.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {
    Contact save(Contact contact);
    List<Contact> findAll();
    boolean existsByContactDetail(String contactDetail);
}
