package com.nevexis.personqueries.model;

public class Person{
	
private Integer id;
	private String firstName;

	private String phone;

	public Person() {
	}

	public Person(String firstName, String phone) {
		this.firstName = firstName;
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
