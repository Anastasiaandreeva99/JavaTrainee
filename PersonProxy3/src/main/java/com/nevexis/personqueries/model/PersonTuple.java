package com.nevexis.personqueries.model;

import com.nevexis.personqueries.services.Loader;

public class PersonTuple extends VersionTuple<Person> {

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

	public void delete() {
		Loader.delete(Person.class, this.getPerson().getId());
	}
	public Person save()
	{
		return Loader.save(Person.class, this.getPerson());
	}
	public PersonTuple load() throws InstantiationException, IllegalAccessException
	{
		Integer id = this.getPerson().getId();
		if(Cache.containsKey(id))
		{
			return new PersonTuple(Cache.getObject(id));
		}
		return new PersonTuple(1,Loader.load(Person.class, id));
	}

}
