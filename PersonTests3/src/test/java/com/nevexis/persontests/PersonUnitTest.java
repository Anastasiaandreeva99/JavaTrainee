package com.nevexis.persontests;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nevexis.personqueries.App;
import com.nevexis.personqueries.model.Person;
import com.nevexis.personqueries.services.PersonService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = App.class)
@AutoConfigureMockMvc
public class PersonUnitTest {
	@Autowired
	PersonService service;
	@Test
	public void isSetPersonSaving()
	{
		service.setPerson("bobi", "bobev");
		List<Person> result = service.getALL().stream().filter(e->e.getFirstName().equals("bobi") && e.getLastName().equals("bobev")).collect(Collectors.toList());
		Assert.assertNotNull(result);
	}
	

}
