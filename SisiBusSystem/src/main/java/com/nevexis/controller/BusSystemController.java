package com.nevexis.controller;

import java.math.BigInteger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.model.City;
import com.nevexis.service.TripService;

@RestController
public class BusSystemController {
	@Autowired
	TripService service;

	@RequestMapping("/cityFrom")
	public Set<City> cityFrom() throws SQLException {
		return service.getFromCities();
	}
	
	@RequestMapping("/cityTo/{cityFrom}")
	public Set<City> cityTo(@PathVariable("cityFrom") String cityFrom) throws SQLException {
		
		return service.getToCities( cityFrom);
	}
	@RequestMapping("/dates/{cityFrom}/{cityTo}")
	public Set<LocalDate> dates(@PathVariable("cityFrom") String cityFrom,@PathVariable("cityTo") String cityTo) throws SQLException {
		
		return service.getDates( cityFrom,cityTo);
	}
	
	
	public BigInteger factoriel()
	{
		
		
		return null;
		
	}

}
