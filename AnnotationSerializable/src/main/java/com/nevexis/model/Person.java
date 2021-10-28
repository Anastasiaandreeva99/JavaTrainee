package com.nevexis.model;
import static com.nevexis.entities.Json.JsonType.*;
import com.nevexis.entities.Json;

public class Person {

	@Json(value = "FirstName", value2 = KAKA)
	private String firstName;

	@Json(value = "LastName", value2 = MAMA)
	private String lastName;

	@Json
	private String address;
	

	public Person(PersonParameter parameterObject) {
		this.firstName = parameterObject.firstName;
		this.lastName = parameterObject.lastName;
		this.address = parameterObject.address;
	}

}
