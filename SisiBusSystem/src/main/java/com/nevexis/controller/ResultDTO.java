package com.nevexis.controller;

import java.time.LocalDate;

public class ResultDTO {
	private LocalDate arrivalDate;
	private String Description;
	private String fromCity;
	private String toCity;
	private Long tripId;

	public ResultDTO(LocalDate arrivalDate, String description, String fromCity, String toCity, Long tripId) {
		super();
		this.arrivalDate = arrivalDate;
		Description = description;
		this.fromCity = fromCity;
		this.toCity = toCity;
		this.tripId = tripId;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getFromCity() {
		return fromCity;
	}

	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}

	public String getToCity() {
		return toCity;
	}

	public void setToCity(String toCity) {
		this.toCity = toCity;
	}

	public Long getTripId() {
		return tripId;
	}

	public void setTripId(Long tripId) {
		this.tripId = tripId;
	}

}
