package vttp2022.csf.miniproject.server.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vttp2022.csf.miniproject.server.models.Contact;

@Repository
public interface AddressBookRepository extends CrudRepository<Contact, String> {
}
