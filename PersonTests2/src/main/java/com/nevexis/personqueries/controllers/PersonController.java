package com.nevexis.personqueries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.personqueries.services.PersonService;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {
	 private final PersonService service;

	    @Autowired
	    public PersonController(PersonService service) {
	        this.service = service;
	    }

	    @GetMapping(value = "/{id}")
	    public ResponseEntity<String> findEntity(@PathVariable String id) {
	        String entity = this.service.findEntity(id);
	        return new ResponseEntity<>(entity, HttpStatus.OK);
	    }

}
