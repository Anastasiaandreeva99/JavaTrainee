package Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Person;
import Repositories.PersonRepo;

@Service
public class PersonService {
	@Autowired
	private PersonRepo personRepo;

	public List<Person> getALL() {
		List<Person> result = personRepo.getData();
		result.stream().forEach(System.out::println);
		return result;

	}

	public void setPerson(String firstName, String lastName) {
		Person newPerson = new Person(firstName, lastName);
		personRepo.saveAndFlush(newPerson);
	}

}
