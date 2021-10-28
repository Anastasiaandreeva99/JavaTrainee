package com.nevexis.controller;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nevexis.model.Account;
import com.nevexis.model.Ledger;
import com.nevexis.model.Type;
import com.nevexis.service.AddAccount;
import com.nevexis.service.AddTransaction;
import com.nevexis.service.GetInformation;

@RestController
public class HelloController {

	@Autowired
    AddAccount addAccount;
	
    @Autowired
    AddTransaction addTrans;
    
    @Autowired
    GetInformation getInfo;

    @RequestMapping("/")
    String hello() {
    
        return "Hello World!";
    }
    
    @RequestMapping("/create/{iban}/{amount}")
	public Object createAccount(@PathVariable("iban") String iban, @PathVariable("amount") long amount)
			throws SQLException {

		BigDecimal balance = new BigDecimal(amount);
		Account newAccount= new Account();
		newAccount.setBalance(balance);
		newAccount.setIban(iban);
		addAccount.addAccount(newAccount);
		return "{ \"iban\": \"" + iban + "\" }";
	}

    @RequestMapping("/transact/{iban1}/{iban2}/{amount}")
	public Object createTransaction(@PathVariable("iban1") String iban1,@PathVariable("iban2") String iban2, @PathVariable("amount") long amount)
			throws SQLException {

		BigDecimal balance = new BigDecimal(amount);
		Ledger trans= new Ledger();
		trans.setAmount(balance);
		trans.setFromIban(iban1);
		trans.setToIban(iban2);
		trans.setType(Type.credit);
		addTrans.AddTrans(trans);
		return "{ \"amount\": \"" + amount + "\" }";
	}

    @RequestMapping("/debits/{iban1}")
	public Object getDebits(@PathVariable("iban1") String iban1 )
			throws SQLException {

		
		return getInfo.getDebits(iban1);
	
		
	}
    @RequestMapping("/credits/{iban1}")
	public Object getCredits(@PathVariable("iban1") String iban1 )
			throws SQLException {

		
		return getInfo.getCredits(iban1);
	
		
	}
    @RequestMapping("/")

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL("https://api.kraken.com/0/public/OHLC?pair=BTCUSD&since=0&interval=1");
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
  
}
