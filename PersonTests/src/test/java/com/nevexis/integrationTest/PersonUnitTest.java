package com.nevexis.integrationTest;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nevexis.personqueries.App;
import com.nevexis.personqueries.services.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = App.class)
@AutoConfigureMockMvc
public class PersonUnitTest {
	@Autowired
	PersonService personService;

	@Test
	public void insertPersonTest() {
		personService.setPerson("sisieta", "sisieta");
		assertNotNull(personService.getPerson("sisieta"));

	}

}
