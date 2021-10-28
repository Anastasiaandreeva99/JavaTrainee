package com.nevexis.personparallel.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nevexis.personparallel.model.Person;

public interface PersonRepo extends JpaRepository<Person, Long> {

	@Query(value = "SELECT p FROM Person p ")
	List<Person> getData();
	
//	@Query(value = "SELECT p FROM Person p WHERE p.id = :personId")
//	Optional<Person> findById(@Param("personId") Long personId);
//	

}
