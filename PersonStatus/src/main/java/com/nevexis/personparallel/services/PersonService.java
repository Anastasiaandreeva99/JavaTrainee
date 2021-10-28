package com.nevexis.personparallel.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.nevexis.personparallel.constants.Status;
import com.nevexis.personparallel.model.Person;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Service
public class PersonService {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public void createPersons() {
		for (int i = 0; i < 1000; i++) {
			Person newPerson = new Person("petko" + i);
			em.persist(newPerson);
		}
	}

	@Transactional
	public void changeStatusInParallel() {
		
		List<Person> persons = new ArrayList<Person>();
		while(!(persons = em.createNativeQuery("SELECT * from Person p  where p.status LIKE ?",Person.class).setParameter(1, "new").setMaxResults(10).getResultList()).isEmpty())
			{persons.parallelStream().forEach(el -> el.setStatus("Proccessed"));
		}
	}

}
