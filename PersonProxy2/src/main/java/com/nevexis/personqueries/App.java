package com.nevexis.personqueries;

import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.model.PersonTuple;
import com.nevexis.personqueries.services.ProxyFactory;

public class App {
	public static void main(String[] args) throws Exception {
		Person person = new Person();
		person.setId(2);
		person.setFirstName("gigi");
		person.setPhone("0904904394");
		PersonTuple personTuple = new PersonTuple(1, person);
	
		ProxyFactory.enhanceWithLock(ProxyFactory.enhanceWithCache(ProxyFactory.enhanceWithOptimisticLock(personTuple))).save();


	}

}