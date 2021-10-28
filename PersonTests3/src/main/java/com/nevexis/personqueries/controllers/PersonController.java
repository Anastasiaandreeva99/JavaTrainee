package com.nevexis.personqueries.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.services.PersonService;
@RequestMapping("/app")
@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	@RequestMapping("/name")
	public String addPerson(@RequestParam String name)  {
    personService.setPerson(name, name);
	 return name;
		
	}
	
	@RequestMapping("/persons")
	public List<Person> getAllPersons()  {
   return  personService.getALL();
		
	}

}
