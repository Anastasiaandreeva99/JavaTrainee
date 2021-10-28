package com.nevexis.model;

public class PersonParameter {
	public String firstName;
	public String lastName;
	public String address;
	public String phone = "";

	public PersonParameter(String firstName, String lastName, String address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = "";
	}

	public PersonParameter(String firstName, String lastName, String address, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}
	
}