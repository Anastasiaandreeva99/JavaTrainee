package com.nevexis.persontests;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nevexis.personqueries.controllers.PersonController;
import com.nevexis.personqueries.services.PersonService;


@RunWith(MockitoJUnitRunner.class)
public class PersonUnitTest {

    @Mock
    private PersonService service;

    private PersonController demoController;

    @Before
    public void setup() {
        this.demoController = new PersonController(this.service);
    }

    @Test
    public void shouldRetrieveAnEntity() {
        Mockito.when(this.service.findEntity("blah")).thenReturn("meh");
        ResponseEntity<String> actualResponse = this.demoController.findEntity("blah");
 
Assert.assertThat(actualResponse.getStatusCode(), Matchers.equalTo(HttpStatus.OK));
      Assert.assertThat(actualResponse.getBody(), Matchers.equalTo("meh"));
    }
}
