package com.nevexis.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BasicService {

	@PersistenceContext
	protected EntityManager em;

}
