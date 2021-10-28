package com.nevexis.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.nevexis.model.DataClass1;
import com.nevexis.model.TransactionData;
import com.nevexis.model.Type;

@Service
public class AccountManager {
	
	@PersistenceContext
	EntityManager entityManager; 

	@Autowired
	private DataSource dataSource;

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void transferAmount(TransactionData trans) throws Exception {
		Connection con = dataSource.getConnection();
		con.setAutoCommit(false);

		try {
			Map<String, Object> paramMap1 = new HashMap<String, Object>();
			paramMap1.put("iban1", trans.getFromIban());
			paramMap1.put("iban2", trans.getToIban());
			paramMap1.put("amount", trans.getAmount());
			validate(paramMap1);

			Map<String, Object> paramMap2 = new HashMap<String, Object>();
			paramMap2.put("from", trans.getFromIban());
			paramMap2.put("to", trans.getToIban());
			paramMap2.put("type", "debit");
			paramMap2.put("amount", trans.getAmount());
			jdbcTemplate.update("INSERT INTO ledger (fromIban,toIban,type,amount) values (:from,:to,:type,:amount)",
					paramMap2);

			Map<String, Object> paramMap3 = new HashMap<String, Object>();
			paramMap3.put("from", trans.getToIban());
			paramMap3.put("to", trans.getFromIban());
			paramMap3.put("type", "credit");
			paramMap3.put("amount", trans.getAmount());
			jdbcTemplate.update("INSERT INTO ledger  (fromIban,toIban,type,amount)  values (:from,:to,:type,:amount)",
					paramMap3);
		} catch (SQLException ex) {
			con.rollback();
		}
		con.commit();

	}

	private void validate(Map<String, Object> paramMap) throws Exception {

		List<Map<String, Object>> accounts = jdbcTemplate
				.queryForList("Select * FROM account WHERE iban = :iban1 or iban = :iban2", paramMap);
		if (accounts.size() >= 2) {

			for (Map<String, Object> account : accounts) {
				if (account.get("iban").equals((String) paramMap.get("iban1"))) {

					BigDecimal amount = new BigDecimal(account.get("balance").toString());
					if (amount.compareTo((BigDecimal) paramMap.get("amount")) < 0)
						throw new Exception("no balance ");
				}

			}
		} else {
			throw new Exception("error in validate");
		}
	}

	public List<TransactionData> getInfoCredit(String iban) throws SQLException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("iban", iban);
		List<TransactionData> result = new ArrayList<TransactionData>();
		List<Map<String, Object>> transactions = jdbcTemplate.queryForList("SELECT * FROM ledger WHERE fromIban=:iban",
				paramMap);
		for (Map<String, Object> trans : transactions) {
			Type newType = Type.debit;
			if (trans.get("type") == "credit")
				newType = Type.credit;
			TransactionData newTrans = new TransactionData(trans.get("fromIban").toString(),
					trans.get("toIban").toString(), newType, (BigDecimal) trans.get("amount"));
			result.add(newTrans);
		}

		return result;
	}

	public List<TransactionData> getInfoDebit(String iban) throws SQLException {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("iban", iban);
		List<TransactionData> result = new ArrayList<TransactionData>();
		List<Map<String, Object>> transactions = jdbcTemplate.queryForList("SELECT * FROM ledger WHERE  toIban=:iban",
				paramMap);
		for (Map<String, Object> trans : transactions) {
			Type newType = Type.debit;
			if (trans.get("type") == "credit")
				newType = Type.credit;
			TransactionData newTrans = new TransactionData(trans.get("fromIban").toString(),
					trans.get("toIban").toString(), newType, (BigDecimal) trans.get("amount"));
			result.add(newTrans);
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<DataClass1> getElements(){
		return entityManager.createNativeQuery("select * from DataClass1", DataClass1.class).getResultList();
	}

}
