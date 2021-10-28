package com.nevexis.PersonQuery;


import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.nevexis.PersonQuery.model.Person;
import com.nevexis.PersonQuery.repositories.PersonRepo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = App.class)
@AutoConfigureMockMvc
public class PersonUnitTest {
	@Autowired
	private PersonRepo personRepo;

	@Test
	void savedUserHasRegistrationDate() {

		List<Person> savedPersons = personRepo.getData();
		Person sisi = new Person("sisi", "sisi");
		assertThat(savedPersons.get(0).getFirstName()).equals(sisi.getFirstName());
	}
}
