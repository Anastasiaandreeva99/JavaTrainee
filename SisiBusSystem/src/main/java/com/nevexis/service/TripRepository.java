package com.nevexis.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nevexis.model.Trip;

public interface TripRepository extends JpaRepository<Trip, Long> {

}
