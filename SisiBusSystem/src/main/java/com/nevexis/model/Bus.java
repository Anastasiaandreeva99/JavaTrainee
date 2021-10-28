package com.nevexis.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bus extends _BaseEntity {

	private String regNumber;

	private Integer numberOfSeats;

	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;

	@ManyToOne
	@JoinColumn(name = "driver2_id")
	private Driver driver2;

	public String getRegNumber() {
		return regNumber;
	}

	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumber_of_seats(Integer numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}
