package vttp.ssf.addressbook.controllers;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import vttp.ssf.addressbook.models.Contact;
import vttp.ssf.addressbook.services.ContactService;

@Controller
@RequestMapping(path="/addressbook")
public class AddressBookController {
    
    private Logger logger = Logger.getLogger(AddressBookController.class.getName());

    // provide access to args used to run Spring Application
	@Autowired
	private ContactService contactSvc;

	@GetMapping
	public String getContactForm(Model model) {
		logger.log(Level.INFO, "Show contact form");
		model.addAttribute("contact", new Contact());
		return "contact";
	}

	@PostMapping("/contact")
	public String postContact(@ModelAttribute Contact contact, Model model,
		HttpServletResponse httpResponse) {
		logger.log(Level.INFO, "ID: " + contact.getId());
		logger.log(Level.INFO, "Name: " + contact.getName());
		logger.log(Level.INFO, "Email: " + contact.getEmail());
		logger.log(Level.INFO, "Phone Number: " + contact.getPhoneNumber());

		contactSvc.save(contact);
		model.addAttribute("contact", contact);

		httpResponse.setStatus(HttpStatus.CREATED.value());
		return "showContact";
	}

	@GetMapping(path="/contact/{contactId}")
	public String getContactById(@PathVariable(value="contactId") String contactId,
		Model model) {
		logger.log(Level.INFO, "Contact ID: " + contactId);

		Contact contact = contactSvc.findById(contactId);
		logger.log(Level.INFO, "Name: " + contact.getName());
		logger.log(Level.INFO, "Email: " + contact.getEmail());
		logger.log(Level.INFO, "Phone Number: " + contact.getPhoneNumber());

		model.addAttribute("contact", contact);
		return "showContact";
	}

	@GetMapping("/contact") 
    public String getAllContact(Model model, @RequestParam(name="startIndex") String startIdx){
        List<Contact> resultFromSvc = contactSvc.findAll(Integer.parseInt(startIdx));
        logger.log(Level.INFO, "resultFromSvc >> " + resultFromSvc);
        model.addAttribute("contacts", resultFromSvc);
        return "listContact";
    }
    // http://localhost:8080/addressbook/contact?startIndex=0 [query string]

}
