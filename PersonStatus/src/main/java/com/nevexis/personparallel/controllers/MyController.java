package com.nevexis.personparallel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.personparallel.services.PersonService;

@RestController
public class MyController {
	@Autowired
	PersonService personService;
	
	@PostMapping("/personCreate")
	public void createPersons() {
		personService.createPersons();
	}
	@PostMapping("/personStatus")
	public void statusPersons() {
		personService.changeStatusInParallel();
	}
	

}
