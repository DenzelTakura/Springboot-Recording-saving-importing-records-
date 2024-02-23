package com.example.personData.serviceimpl;

import com.example.personData.model.Contact;
import com.example.personData.model.Dtos.ContactDto;
import com.example.personData.repository.ContactRepo;
import com.example.personData.repository.PersonRepo;
import com.example.personData.service.ContactService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepo contactRepo;
    private final PersonRepo personRepo;

    public ContactServiceImpl(ContactRepo contactRepo, PersonRepo personRepo) {
        this.contactRepo = contactRepo;
        this.personRepo = personRepo;
    }

    @Override
    public void getContactsFromSystem() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://192.168.32.33:8081/contact/all";
        ResponseEntity<List<ContactDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ContactDto>>() {
                }
        );
        List<ContactDto> contactDtos = response.getBody();
        for (ContactDto contactDto : contactDtos) {

           if (!(personRepo.findPersonByIdNumber(contactDto.getPerson().getIdNumber()) == null)) {

                Contact contact = new Contact();
                contact.setContactDetail(contactDto.getContactDetail());
                contact.setPerson(personRepo.findPersonByIdNumber(contactDto.getPerson().getIdNumber()));
               System.out.println("fetched contact for : "+contactDto.getPerson().getFirstName()+ "  "+contactDto.getPerson().getLastName());
               contactRepo.save(contact);
            }else
                System.err.println("every contact should point to a person....skipping for unavailable person: "+contactDto.getPerson().getFirstName()+ "  "+contactDto.getPerson().getLastName());

        }
    }

    @Override
    public Contact add(Contact contact) {
        Contact[] contacts = findAll().toArray(new Contact[0]);
        if(!(contactCheck(contact,contacts)==null) && contactDetailUnique(contact.getContactDetail())){
            return contactRepo.save(contact);
        }
        return null;
    }

    @Override
    public Contact contactCheck(Contact contact, Contact[] contacts) {
        int counter =0;
        for (Contact cont : contacts) {
            //count number of contacts each trainee has
            if (contact.getPerson().equals(cont.getPerson())) {
                counter += 1;
                if (counter == 5) {
                    System.err.println("Enough Contacts for one person :  "+contact.getPerson().getFirstName()+ " "+ contact.getPerson().getLastName());
                    return null;
                }
            }else {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepo.findAll();
    }

    @Override
    public boolean contactDetailUnique(String contactDetail) {
        return !contactRepo.existsByContactDetail(contactDetail);
    }
}