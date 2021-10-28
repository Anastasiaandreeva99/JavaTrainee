package com.nevexis.personparallel.services;

import java.util.Objects;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nevexis.personparallel.model.Person;

@Service
public class PersonService {

	@Autowired
	EntityManager em;

	public Stream<Person> getAll() {
		return getPersonWithChilds(em.find(Person.class, 0)).filter(el -> Objects.nonNull(el));
	}

	public Stream<Person> getPersonWithChilds(Person root) {

		return null != root.getChild()
				? Stream.concat(hasChildWithFive(root, 1) == true ? Stream.of(root) : null, root.getChild().stream()
						.map(el -> getPersonWithChilds(el)).reduce(Stream.empty(), (s1, s2) -> Stream.concat(s1, s2)))
				: Stream.empty();

	}

	public boolean hasChildWithFive(Person person, int level) {
		return level == 0 ? person.getChild().size() == 5
				: person.getChild().stream().anyMatch(el -> hasChildWithFive(el, level - 1) == true);

	}
	
	public boolean hasChildWithFive(Person person)
	{
		return false;
		
	}

}
