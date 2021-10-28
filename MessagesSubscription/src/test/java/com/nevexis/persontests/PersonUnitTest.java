//package com.nevexis.persontests;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.nevexis.personqueries.App;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = App.class)
//@AutoConfigureMockMvc
//public class PersonUnitTest {
//
//	@Autowired
//	private PersonRepo personRepo;
//
//	@Test
//	void TestOrderByName() {
//
//		Person newPerson = new Person("petko", "dimitrov");
//		personRepo.saveAndFlush(newPerson);
//
//		List<Person> savedPersons = personRepo.getData();
//		assertEquals(savedPersons.get(0).getFirstName(), newPerson.getFirstName());
//	}
//
//}
