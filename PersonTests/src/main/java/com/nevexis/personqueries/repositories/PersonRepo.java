package com.nevexis.personqueries.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevexis.personqueries.model.Person;

public interface PersonRepo extends JpaRepository<Person, Long> {

	@Query(value = "SELECT p FROM Person p ")
	List<Person> getData();

	@Query(value = "SELECT p FROM Person p where p.firstName = ?1")
	Person getPerson(String firstName);
	
	@Query(value = "SELECT p FROM Person p where p.id = ?1")
	Person getPersonById(Long id);

}
