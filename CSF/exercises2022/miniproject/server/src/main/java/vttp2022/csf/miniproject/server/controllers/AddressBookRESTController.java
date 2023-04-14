package vttp2022.csf.miniproject.server.controllers;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp2022.csf.miniproject.server.models.Contact;
import vttp2022.csf.miniproject.server.models.Response;
import vttp2022.csf.miniproject.server.services.AddressBookService;

@RestController
@RequestMapping(path = "/api/addressbook", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressBookRESTController {

    private Logger logger = Logger.getLogger(AddressBookRESTController.class.getName());

    @Autowired
    private AddressBookService addressBookSvc;

    @PostMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteContact(@RequestBody String payload) {
        logger.info("Payload: %s".formatted(payload));

        Contact c;
        Response resp = new Response();

        try {
            c = Contact.create(payload);
            logger.info("delete contact: %s".formatted(c.getEmail()));
            addressBookSvc.deleteContact(c.getEmail());
        } catch (Exception e) {
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resp.toJson().toString());
        }

        resp.setCode(200);
        resp.setMessage("Delete contact: %s".formatted(c.getEmail()));
        resp.setData(c.toJson().toString());

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(resp.toJson().toString());
    }
    
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postContact(@RequestBody String payload) {

        logger.info("Payload: %s".formatted(payload));

        Contact c;
        Response resp = new Response();

        try {
            c = Contact.create(payload);
            logger.info("Create contact: %s".formatted(c.getEmail()));
            addressBookSvc.save(c);
        } catch (Exception e) {
            resp.setCode(400);
            resp.setMessage(e.getMessage());
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(resp.toJson().toString());
        }

        resp.setCode(201);
        resp.setMessage(c.getEmail());
        resp.setData(c.toJson().toString());

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(resp.toJson().toString());
    }

    @GetMapping(path = "/list")
    public ResponseEntity<String> getContactsList() {

        List<Contact> contacts = addressBookSvc.listContacts();
        logger.info("List contacts: %s".formatted(contacts.size()));
        JsonArrayBuilder contactsBuilder = Json.createArrayBuilder();
        for (Contact c: contacts) {
            contactsBuilder.add(c.toJson());
        }
        JsonObject contactsList = Json.createObjectBuilder()
            .add("contacts", contactsBuilder)
            .build();

        return ResponseEntity.ok(contactsList.toString());
    }
}
