package com.nevexis.service;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nevexis.model.Ledger;
import com.nevexis.model.Type;

@Service
@Transactional
public class AddTransaction extends BaseService {

	public void AddTrans(Ledger trans) {
		if (valid(trans)) {

			em.persist(trans);
			updateAccounts(trans);
			Ledger transDebit = new Ledger();
			transDebit.setFromIban(trans.getToIban());
			transDebit.setToIban(trans.getFromIban());
			transDebit.setAmount(trans.getAmount());
			transDebit.setType(Type.credit);
			em.persist(transDebit);
		}

	}

	public boolean valid(Ledger trans) {
		Query query1 = em.createQuery("select a from Account a where iban=:iban and balance>=:amount");
		query1.setParameter("amount", trans.getAmount());
		query1.setParameter("iban", trans.getFromIban());
		if (query1.getResultList().size() == 0)
			return false;
		Query query2 = em.createQuery("select a from Account a where iban=:iban");
		query2.setParameter("iban", trans.getToIban());
		if (query2.getResultList().size() == 0)
			return false;
		return true;

	}

	public void updateAccounts(Ledger trans) {

		Query query1 = em.createQuery("update Account set balance=balance-:amount where iban=:iban");
		query1.setParameter("amount", trans.getAmount());
		query1.setParameter("iban", trans.getFromIban()).executeUpdate();

		Query query2 = em.createQuery("update Account set balance=balance+:amount where iban=:iban");
		query2.setParameter("amount", trans.getAmount());
		query2.setParameter("iban", trans.getToIban()).executeUpdate();

	}
	
	
}
