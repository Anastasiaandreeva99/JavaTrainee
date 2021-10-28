package com.nevexis.main;

import com.nevexis.entities.EntityProcessor;
import com.nevexis.model.Person;
import com.nevexis.model.PersonParameter;

public class Application {

	public static void main(String[] args) throws Exception {

		Person newPerson = new Person(new PersonParameter("Ivan", "Ivanov", "1990"));
		EntityProcessor processor = new EntityProcessor();
		processor.serialize(newPerson);
	}

}
