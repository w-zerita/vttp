package vttp.ssf.addressbook.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.addressbook.models.Contact;
import vttp.ssf.addressbook.services.ContactService;

@Controller
@RequestMapping(path="/addressbook")
public class AddressBookController {

	private Logger logger = Logger.getLogger(AddressBookController.class.getName());

    @Autowired
    private ApplicationArguments appArgs;

    @GetMapping("/")
    public String contactForm(Model model) {
        logger.log(Level.INFO, "Show contact form");
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String ContactServiceubmit(@ModelAttribute Contact contact, Model model) {
        logger.log(Level.INFO, "ID: " + contact.getId());
        logger.log(Level.INFO, "Name: " + contact.getName());
        logger.log(Level.INFO, "Email: " + contact.getEmail());
        logger.log(Level.INFO, "Phone Number: " + contact.getPhoneNumber());

        ContactService ct = new ContactService();
        ct.saveContact(contact, model, appArgs);
        return "showContact";
    }

    @GetMapping("/contact/{contactId}")
    public String getContact(Model model, @PathVariable (value="contactId") String contactId) {
        logger.log(Level.INFO, "Contact ID: " + contactId);
        ContactService ct = new ContactService();
        ct.getContactById(model, contactId, appArgs);
        return "showContact";
    }
}
