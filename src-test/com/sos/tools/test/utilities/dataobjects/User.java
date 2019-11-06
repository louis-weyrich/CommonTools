package com.sos.tools.test.utilities.dataobjects;

public class User {

	protected String firstName;
	protected String lastName;
	protected String userName;
	protected Contact contact;
	
	
	public User()
	{
		
	}
	
	public User(String firstName, String lastName, String userName, Contact contact)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.contact = contact;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Contact getContact() {
		return contact;
	}
	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
