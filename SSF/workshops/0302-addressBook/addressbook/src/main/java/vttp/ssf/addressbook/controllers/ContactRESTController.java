package vttp.ssf.addressbook.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import vttp.ssf.addressbook.models.Contact;
import vttp.ssf.addressbook.services.ContactService;

@RestController
public class ContactRESTController {
    
    private Logger logger = Logger.getLogger(ContactRESTController.class.getName());
    
    @Autowired
    private ApplicationArguments applicationArguments;

    @PostMapping("/contact2")
    public Contact ContactServiceubmitRest(@RequestBody Contact contact, 
        Model model, HttpServletResponse httpResponse){
        logger.log(Level.INFO, "Id : " + contact.getId());
        logger.log(Level.INFO, "Name : " + contact.getName());
        logger.log(Level.INFO, "Email : " + contact.getEmail());
        logger.log(Level.INFO, "Phone Number : " + contact.getPhoneNumber());
        ContactService ct = new ContactService();
        ct.saveContact(contact, model, applicationArguments);
        httpResponse.setStatus(HttpStatus.CREATED.value());
        return contact;
    }
}
