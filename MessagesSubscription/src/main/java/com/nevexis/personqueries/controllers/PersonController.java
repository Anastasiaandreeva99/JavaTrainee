package com.nevexis.personqueries.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nevexis.personqueries.services.PersonService;

@Controller
public class PersonController {
	@Autowired
	private PersonService personService;

	@RequestMapping("/persons")
	public String persons()  {
    personService.setPerson("sisi", "sisi");
	 personService.getALL().stream().forEach(System.out::println);
	 return "";
		
	}

}
