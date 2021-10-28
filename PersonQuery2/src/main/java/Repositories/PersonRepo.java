package Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import Model.Person;



public interface PersonRepo extends JpaRepository<Person, String> {
	@Query(value = "SELECT * FROM Person p LIMIT 1000", nativeQuery = true)
	List<Person> getData();

}
