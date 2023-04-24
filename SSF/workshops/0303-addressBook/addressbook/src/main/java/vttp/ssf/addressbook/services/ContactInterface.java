package vttp.ssf.addressbook.services;

import java.util.List;

import vttp.ssf.addressbook.models.Contact;

public interface ContactInterface {
    public void save(final Contact c);
	public Contact findById(final String contactId);
	public List<Contact> findAll(int startIndex);
}
