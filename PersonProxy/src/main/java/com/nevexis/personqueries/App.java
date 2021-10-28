package com.nevexis.personqueries;

import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.model.PersonTuple;
import com.nevexis.personqueries.services.Loader;
import com.nevexis.personqueries.services.LockServiceImpl;

public class App {
	public static void main(String[] args) throws Exception {
		Person person = new Person();
		person.setId(4);
		person.setFirstName("gogo");
		person.setPhone("039000093");
		
		LockServiceImpl<Person> lockService = new LockServiceImpl<Person>();
		PersonTuple personTuple1=lockService.load(PersonTuple.class, 4);
		PersonTuple personTuple2=lockService.load(PersonTuple.class, 4);
		lockService.save(personTuple1);
		lockService.load(PersonTuple.class, 3);
		lockService.save(personTuple2);


		
	}


}