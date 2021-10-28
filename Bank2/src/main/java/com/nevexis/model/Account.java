package com.nevexis.model;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Account {
	@Autowired
	DataSource dataSource;

	// BigDecimal
	public void add(String iban, BigDecimal balance) throws SQLException {
		PreparedStatement pstAdd = dataSource.getConnection().prepareStatement("insert INTO  account VALUES (?,?)");
		pstAdd.setString(1, iban);
		pstAdd.setBigDecimal(2, balance);
		pstAdd.executeUpdate();
	}
}
