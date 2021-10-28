package com.nevexis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.service.BusSystem;

@RestController
public class BusSystemController {
	@Autowired
	BusSystem service;

}
