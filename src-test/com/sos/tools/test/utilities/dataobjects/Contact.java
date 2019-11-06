package com.sos.tools.test.utilities.dataobjects;

public class Contact {

	
	protected Address address;
	protected String honePhone;
	
	public Contact()
	{
		
	}
	
	public Contact(Address address, String phone)
	{
		this.address = address;
		this.honePhone = phone;
	}
	

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address homeAddress) {
		this.address = homeAddress;
	}
	public String getHonePhone() {
		return honePhone;
	}
	public void setHonePhone(String honePhone) {
		this.honePhone = honePhone;
	}
}
