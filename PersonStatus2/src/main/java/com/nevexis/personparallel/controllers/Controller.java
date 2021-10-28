package com.nevexis.personparallel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.personparallel.model.PersonDTO;
import com.nevexis.personparallel.services.PersonService;

@RestController
public class Controller {
	@Autowired
	private PersonService serv;

	@RequestMapping("/person/{personName}")
	public PersonDTO deposit(@PathVariable("personName") String personName) {
		return serv.findPersonByName(personName);

	}

}
