package vttp2022.csf.miniproject.server.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp2022.csf.miniproject.server.models.Contact;
import vttp2022.csf.miniproject.server.repositories.AddressBookRepository;

@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepo;

    public List<Contact> listContacts() {
        List<Contact> contacts = new ArrayList<>();
        addressBookRepo.findAll().forEach(contacts::add);
        return contacts;
    }

    public void save(final Contact c) {
        addressBookRepo.save(c);
    }

    public void deleteContact(String email) {
        addressBookRepo.deleteById(email);
    }

    // private static final String CONTACT_KEY = "addressbook";

    // @Autowired
    // private RedisTemplate<String, Object> redisTemplate;

    // @Override
    // public Contact findByEmail(String email) {
    //     Contact c = (Contact) redisTemplate.opsForHash()
    //         .get(CONTACT_KEY, email);
    //     return c;
    // }

    // @Override
    // public List<Contact> listContacts() {
    //     List<Object> fromAddressBook = redisTemplate.opsForList()
    //         .range(CONTACT_KEY, 0, -1);

    //     List<Contact> contacts = (List<Contact>) redisTemplate.opsForHash()
    //         .multiGet(CONTACT_KEY + "_details", fromAddressBook)
    //         .stream()
    //         .filter(Contact.class::isInstance)
    //         .map(Contact.class::cast)
    //         .toList();

    //     return contacts;
    // }

    // @Override
    // public void save(final Contact c) {
    //     if (redisTemplate.opsForList().indexOf(CONTACT_KEY, c.getEmail()) == null) {
    //         // key: addressbook, element: email
    //         redisTemplate.opsForList().leftPush(CONTACT_KEY, c.getEmail());
    //     } 
    //     // key: addressbook, field: email, value: contact
    //     redisTemplate.opsForHash().putIfAbsent(CONTACT_KEY + "_details", c.getEmail(), c);
    // }

    // @Override
    // public void deleteContact(String email) {
    //     redisTemplate.opsForList().remove(CONTACT_KEY, 0, email);
    //     redisTemplate.opsForHash().delete(CONTACT_KEY + "_details", email);
    // }
    
}
