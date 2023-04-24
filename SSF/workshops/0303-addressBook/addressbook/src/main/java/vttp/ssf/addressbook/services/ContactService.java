package vttp.ssf.addressbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import vttp.ssf.addressbook.models.Contact;

@Service
public class ContactService implements ContactInterface {

    private static final String CONTACT_ENTITY = "contactlist";

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
	public void save(final Contact c) {
		// key: contactlist, element: contactId
		redisTemplate.opsForList().leftPush(CONTACT_ENTITY, c.getId());
		// key: contactlist_Map, field: contactId, value: contact
		redisTemplate.opsForHash().put(CONTACT_ENTITY + "_Map", c.getId(), c);
        System.out.println("Save contact");
	}

	@Override
	public Contact findById(final String contactId) {
		Contact result = (Contact) redisTemplate.opsForHash()
			.get(CONTACT_ENTITY + "_Map", contactId);
		return result;
	}

	@Override
	public List<Contact> findAll(int startIndex) {
		List<Object> fromContactList = redisTemplate.opsForList()
			.range(CONTACT_ENTITY, startIndex, startIndex + 9); 
			// key, startIndex, endIndex

		List<Contact> contacts = (List<Contact>) redisTemplate.opsForHash()
			.multiGet(CONTACT_ENTITY + "_Map", fromContactList)
			.stream()
			.filter(Contact.class::isInstance)
			.map(Contact.class::cast)
			.toList();

		return contacts;
	}
    
}
