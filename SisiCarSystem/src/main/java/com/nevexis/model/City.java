package com.nevexis.model;




@Entity
@NamedQuery(name = Constants.selectCityByName , query = "select c from City c where  c.name=:cityName")
public class City extends _BaseEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
