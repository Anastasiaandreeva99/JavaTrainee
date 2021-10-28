package com.nevexis.model;

import javax.persistence.Entity;

@Entity
public class City extends _BaseEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
