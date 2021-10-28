package com.nevexis.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nevexis.model.Account;

@Service
@Transactional
public class AddAccount extends BaseService {

	public void addAccount(Account newAccount) {
		em.persist(newAccount);
	}

}
