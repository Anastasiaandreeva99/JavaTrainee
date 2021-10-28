package com.nevexis.personparallel.services;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevexis.personparallel.constants.PersonConstants;
import com.nevexis.personparallel.model.Person;
import com.nevexis.personparallel.model.PersonDTO;
import com.nevexis.personparallel.model._MessageFormatFast;
import com.nevexis.personparallel.repositories.PersonRepo;
@Service
public class PersonService {

	@Autowired
	private PersonRepo personRepo;
	@PersistenceContext
	EntityManager em;
	private static final Logger LOGGER = Logger.getLogger(Person.class.getName());

	public Stream<Person> getPersonSortedByName() {

		LOGGER.info("getting all data");
		return personRepo.getData().parallelStream()
				.sorted(Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName));

	}

	public Stream<Person> getPersonSortedByAge() {

		return personRepo.getData().parallelStream().sorted(Comparator.comparing(Person::getAge));

	}

	public Long countPerson() {

		return personRepo.getData().parallelStream().count();
	}

	public Long countPersonUnderAge(Integer age) {

		if (age < 0)
			LOGGER.log(Level.WARNING, "Age is under zero");

		return personRepo.getData().parallelStream().filter(e -> e.getAge() < age).count();
	}

	public Double getAverageAge() {

		return personRepo.getData().parallelStream().mapToInt(Person::getAge).average().orElse(0);
	}

	public Stream<String> getOrderedPersonNamesStartingWithA() {

		Pattern regex = Pattern.compile("^a[a-z]+");
		return personRepo.getData().stream().map(e -> e.getFirstName()).filter(regex.asPredicate()).sorted();
	}

	public void add(Person newPerson) {

		LOGGER.log(Level.INFO, "Sisi is saving some data");

		personRepo.saveAndFlush(newPerson);
	}

	public Stream<Person> findParents(Person person) {
		return null != person ? Stream.concat(findParents(person.getParent()), Stream.of(person)) : Stream.empty();
	}

	static ThreadLocal<MessageFormat> cachedFormatter = ThreadLocal
			.withInitial(() -> new MessageFormat("select p from Person p where p.firstName = {0}"));
	{

	}

	public PersonDTO findPersonByName(String personName) {
		List<Person> persons = em.createQuery(cachedFormatter.get().format(personName), Person.class).setMaxResults(1)
				.getResultList();

		return persons.size() >= 1 ? new PersonDTO(persons.get(0).getFirstName()) : new PersonDTO("lonely");

	}

	public Stream<Person> of(Long personId) {
		return Stream.of(em.find(Person.class, personId))
				.map(p1 -> Stream.concat(Stream.of(p1),
						null == p1.getParent() ? Stream.empty() : of(p1.getParent().getId())))
				.reduce(Stream.empty(), (s1, s2) -> Stream.concat(s1, s2));
	}

	



}
