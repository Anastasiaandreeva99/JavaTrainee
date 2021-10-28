package com.nevexis.personqueries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.services.PersonService;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
	@Autowired
	private PersonService personService;

	@PostMapping(path = "/{personName}")
	public ResponseEntity<Object> findPerson(@RequestBody String personName) {
		Person person = personService.getPerson(personName);
	    return new ResponseEntity<>(person, HttpStatus.OK);

	}

}
