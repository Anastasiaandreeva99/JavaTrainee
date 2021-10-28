package com.nevexis.PersonQuery.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nevexis.PersonQuery.model.Person;



public interface PersonRepo extends JpaRepository<Person, String> {
	@Query(value = "SELECT * FROM Person p LIMIT 100", nativeQuery = true)
	List<Person> getData();

}
