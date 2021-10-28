package com.nevexis.personqueries.model;

public class PersonTuple extends VersionTuple<Person>{

	public PersonTuple(Integer version, Person person) {
		super.version = version;
		this.setPerson(person);
	}

	public PersonTuple() {
	}


	public PersonTuple(PersonTuple object) {
		super.version = object.getVersion();
		this.setPerson(object.getPerson());
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Person getPerson() {
		return super.object;
	}

	public void setPerson(Person person) {
		super.object = person;
	}


}
