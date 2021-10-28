package com.nevexis.personqueries.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.repositories.PersonRepo;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepo personRepo;

	public List<Person> getALL() {
		return personRepo.getData();
	}
	public Person getPerson(String firstName) {
		return personRepo.getPerson(firstName);
	}
	public String getPersonById(Long id)
	{
		return personRepo.getPersonById(id).getFirstName();
	}
	public void setPerson(String firstName, String lastName) {
		Person newPerson = new Person(firstName, lastName);
		personRepo.saveAndFlush(newPerson);
	}


}
