package com.sos.tools.test.utilities.dataobjects;

import com.sos.tools.utilities.CloneCopy;

public class Address extends CloneCopy{
	
	protected String address1;
	protected String address2;
	protected String city;
	protected String state;
	protected String zipp;
	protected boolean active;
	
	public Address()
	{
		
	}
	
	
	public Address(String address1, String address2, String city, String state, String zipp)
	{
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zipp = zipp;
	}

	public String getAddress1() {
		return address1;
	}
	
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	
	public String getAddress2() {
		return address2;
	}
	
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZipp() {
		return zipp;
	}
	
	public void setZipp(String zipp) {
		this.zipp = zipp;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
