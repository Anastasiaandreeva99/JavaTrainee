package com.nevexis.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.nevexis.model.City;
import com.nevexis.model.Transition;
import com.nevexis.model.Trip;

@Service
public class TripService extends BasicService {

	@Autowired
	TripRepository tripsRepo;

	public Set<City> getFromCities() {

		Set<City> citiesFrom = new HashSet<>();

		tripsRepo.findAll().parallelStream().forEach(trip -> {
			List<Transition> transitions = trip.getTransitions();
			transitions.sort(Comparator.comparing(tr -> tr.getOrderNumber()));
			int index = transitions.size() - 1;
			transitions.remove(index);
			transitions.stream().forEach(trans -> {
				citiesFrom.add(trans.getCity());
			});
		});

		return citiesFrom;

	}

	public Set<City> getToCities(String fromCity) {
		List<Trip> trips = tripsRepo.findAll();

		Set<City> citiesTo = new HashSet<>();

		trips.stream().forEach(trip -> {
			List<Transition> transitions = trip.getTransitions();
			transitions.sort(Comparator.comparing(tr -> tr.getOrderNumber()));

			Integer startCityTransiition = transitions.stream().parallel()
					.filter(trans -> trans.getCity().getName().equals(fromCity)).findFirst()
					.map(trans -> trans.getOrderNumber()).orElse(null);

			citiesTo.addAll(transitions.stream().parallel().filter(tr -> tr.getOrderNumber() > startCityTransiition)
					.map(tr -> tr.getCity()).collect(Collectors.toSet()));
		});
		return citiesTo;

	}

	public Set<LocalDate> getDates(String fromCity, String toCity) {

		List<Trip> trips = tripsRepo.findAll();

		Set<LocalDate> dates = new HashSet<>();

		LocalDate date = LocalDate.now();

		trips.stream().parallel().forEach(trip -> {

			List<Transition> transitions = trip.getTransitions();
			transitions.sort(Comparator.comparing(tr -> tr.getOrderNumber()));

			Transition startCityTransition = transitions.stream()
					.filter(trans -> trans.getCity().getName().equals(fromCity)).findFirst().orElse(null);
			Transition finalCityTransition = transitions.stream()
					.filter(trans -> trans.getCity().getName().equals(toCity)).findFirst().orElse(null);
			if (null != startCityTransition && null != finalCityTransition) {
				if (startCityTransition.getOrderNumber() < finalCityTransition.getOrderNumber()) {
					date.with(TemporalAdjusters.next(DayOfWeek.valueOf(startCityTransition.getWeekday().toString())));
				}
			}

		});
		LocalDate date2 = date;
		for (int i = 0; i <= 100; i++) {
			dates.add(date2);
			date2 = date2.plusDays(7);
		}
		return dates;
	}

//

}
