package com.nevexis.personparallel.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NamedQuery;

import com.nevexis.personparallel.constants.PersonConstants;



@Entity
@NamedQuery(name = PersonConstants.selectPersonByName, query = "select p from Person p where p.firstName=:firstName")
public class Person  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;
	
	private Integer age;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Person parent;

	public Person() {
	}

	public Person(String firstName, String lastName,Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}


	public Person getParent() {
		return parent;
	}

	public void setParent(Person parent) {
		this.parent = parent;
	}

	public Long getId() {
		return id;
	}

	
	

}
