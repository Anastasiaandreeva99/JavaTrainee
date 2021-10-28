package com.nevexis.db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController1 {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    private AddAccount account;
//     @Autowired
//    private Transaction transact;   
    @Autowired
	DataSource dataSource;
    
    @RequestMapping("/")
    public String hello() throws Exception {
    BigDecimal amount = new BigDecimal(500);    	
   // account.add( "BG77000",amount);
  	PreparedStatement getAccounts = dataSource.getConnection().prepareStatement("Select * FROM  account LIMIT 1");
   	ResultSet result = getAccounts.executeQuery();     
   // transact.Transact("BG000JJ4343TT889","BG55YY34877834784",amount);
    //return result.getString("iban");
    return "hello";
   	
    }



}
