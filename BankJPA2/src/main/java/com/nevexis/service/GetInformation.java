package com.nevexis.service;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nevexis.model.Ledger;

@Service
@Transactional
public class GetInformation extends BaseService {

	public String getDebits(String iban) {
		List<Ledger> resultList = em.createQuery("select l from Ledger l where  toIban=:iban")
				.setParameter("iban", iban).getResultList();
		return new Gson().toJson(resultList);

	}
	public String getCredits(String iban) {
		List<Ledger> resultList = em.createQuery("select l from Ledger l where fromIban=:iban")
				.setParameter("iban", iban).getResultList();
		return new Gson().toJson(resultList);

	}


}
