package com.nevexis.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.nevexis.model.Account;
import com.nevexis.model.DataClass1;
import com.nevexis.model.TransactionData;
import com.nevexis.model.Type;
import com.nevexis.service.AccountManager;

@RestController
public class HelloController {

	@Autowired
	private AccountManager trans;
	
	@Autowired
	private Account newAccount;
	
	@Autowired
	private AccountManager accountManager;

	@GetMapping(path = "/")
	public String hello() throws Exception {
		Type data = Type.debit;
		trans.transferAmount(new TransactionData("B", "BNM", data, new BigDecimal(500)));
		return "oki doki ";
	}

	@GetMapping(path = "/elements")
	public List<DataClass1> elements() throws Exception {
		return accountManager.getElements();
	}

	@GetMapping("/transact")
	public Object newTransaction(@RequestParam(name = "from", required = true) String from,
			@RequestParam(name = "to", required = true) String to,
			@RequestParam(name = "amount", required = true) long amount) throws Exception {
		BigDecimal balance = new BigDecimal(amount);
		Type data = Type.debit;

		TransactionData trans = new TransactionData(from, to, data, balance);
		accountManager.transferAmount(trans);

		return "{ \"sum\": \"" + amount + "\" }";

	}

	@GetMapping("/listInfo/{iban}/{type}")
	public Object listTransactionsCredit(@PathVariable("iban") String iban, @PathVariable("type") String type)
			throws SQLException {
		if (type.equals("credit")) {
			return new Gson().toJson(accountManager.getInfoCredit(iban));
		} else
			return new Gson().toJson(accountManager.getInfoDebit(iban));

	}

	@GetMapping("/create/{iban}/{amount}")
	public Object createAccount(@PathVariable("iban") String iban, @PathVariable("amount") long amount)
			throws SQLException {

		BigDecimal balance = new BigDecimal(amount);
		newAccount.add(iban, balance);
		return "{ \"iban\": \"" + iban + "\" }";
	}

}
