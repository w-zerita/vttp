package vttp.ssf.addressbook.models;

import java.io.Serializable;
import java.util.Random;

public class Contact implements Serializable {
	private String id;
	private String name;
	private String email;
	private String phoneNumber;

	// GETTERS and SETTERS
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    // Constructors
	public Contact() {
		this.id = generateId(8);
	}

	public Contact(String id, String name, String email, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Contact(String name, String email, String phoneNumber) {
		this.id = generateId(8);
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	
	// Methods
	@Override
	public String toString() {
		return "Contact [email=" + email + ", id=" + id + ", name=" + name 
			+ ", phoneNumber=" + phoneNumber + "]";
	}

	private synchronized String generateId(int numChars) {
		Random r = new Random();
		StringBuilder sb = new StringBuilder();
		while(sb.length() < numChars) {
			sb.append(Integer.toHexString(r.nextInt()));
		}
		return sb.toString().substring(0, numChars); 
		// returns substring from index 0 to numChars
	}
}
