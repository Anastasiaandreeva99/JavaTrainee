package com.nevexis.personparalleltest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nevexis.personparallel.App;
import com.nevexis.personparallel.model.Person;
import com.nevexis.personparallel.services.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = App.class)
@AutoConfigureMockMvc
public class PersonParalleUnitTest {
	
	@Autowired
	private PersonService personService;

	@Test
	public void PersonTest() {

		Person newPerson1 = new Person("petko", "petko",14);
		Person newPerson2 = new Person("georgi", "georgi",28);
		Person newPerson3 = new Person("anastasia", "sisi",16);
		Person newPerson4 = new Person("ivana", "ivana",36);
		
		
		newPerson1.setParent(newPerson2);
		newPerson2.setParent(newPerson3);
		newPerson3.setParent(newPerson4);
		newPerson4.setParent(null);
		
	
		
		personService.add(newPerson4);
		personService.add(newPerson3);
		personService.add(newPerson2);
		personService.add(newPerson1);

		
		List<Person> expected = Arrays.asList(null,newPerson4,newPerson3,newPerson2,newPerson1);

		assertEquals(personService.findParents(newPerson1).collect(Collectors
                .toCollection(ArrayList::new)),expected);
		
	}

}
