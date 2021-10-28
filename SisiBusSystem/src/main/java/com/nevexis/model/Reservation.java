package com.nevexis.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation extends _BaseEntity {

	private int seat_num;

	private Time leaving_date;

	private Time arrival_date;

	private float price;

	@ManyToOne
	@JoinColumn(name = "city_from_id")
	private City city_from;

	@ManyToOne
	@JoinColumn(name = "city_to_id")
	private City city_to;

	@ManyToOne
	@JoinColumn(name = "trip_id")
	private Trip trip;

	public int getSeat_num() {
		return seat_num;
	}

	public void setSeat_num(int seat_num) {
		this.seat_num = seat_num;
	}

	public Time getLeaving_date() {
		return leaving_date;
	}

	public void setLeaving_date(Time leaving_date) {
		this.leaving_date = leaving_date;
	}

	public Time getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Time arrival_date) {
		this.arrival_date = arrival_date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public City getCity_from() {
		return city_from;
	}

	public void setCity_from(City city_from) {
		this.city_from = city_from;
	}

	public City getCity_to() {
		return city_to;
	}

	public void setCity_to(City city_to) {
		this.city_to = city_to;
	}

	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
	}

	public Reservation(int seat_num, Time leaving_date, Time arrival_date, float price, City city_from, City city_to,
			Trip trip) {
		super();
		this.seat_num = seat_num;
		this.leaving_date = leaving_date;
		this.arrival_date = arrival_date;
		this.price = price;
		this.city_from = city_from;
		this.city_to = city_to;
		this.trip = trip;
	}

}
