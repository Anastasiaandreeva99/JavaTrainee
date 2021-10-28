package com.nevexis.db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Transaction {
	@Autowired
	DataSource dataSource;
	
   public void Transact(String ibanFrom,String ibanTo,BigDecimal amount) throws Exception
   {
	   PreparedStatement pstAccountFind = dataSource.getConnection().prepareStatement("Select * FROM account WHERE iban = ?");
	   PreparedStatement pstEnoughMoney = dataSource.getConnection().prepareStatement("Select balance FROM account WHERE iban = ? LIMIT 1");
	   pstAccountFind.setObject(1,ibanFrom);
	   if(pstAccountFind.executeQuery()==null)
	   {
		   throw new Exception("account not found");
	   }
	   pstAccountFind.setObject(1,ibanTo);
	   if(pstAccountFind.executeQuery()==null)
	   {
		   throw new Exception("account not found");
	   }
	   ResultSet rs = pstEnoughMoney.executeQuery();
	   if(rs.getBigDecimal("balance").compareTo(amount) ==-1)
	   {
		   throw new Exception("not enough money");
	   }
	   dataSource.getConnection().setAutoCommit(false);
	   PreparedStatement add1 = dataSource.getConnection().prepareStatement("INSERT INTO transaction (?,?,?,?)");
	   add1.setObject(1, ibanFrom);
	   add1.setObject(2, ibanTo);
	   add1.setObject(3, "credit");
	   add1.setObject(4, amount);
	   add1.execute();
	   add1.setObject(1, ibanTo);
	   add1.setObject(2, ibanFrom);
	   add1.setObject(3, "debit");
	   add1.setObject(4, amount);
	   add1.execute();
	   PreparedStatement update1 = dataSource.getConnection().prepareStatement("UPDATE account SET amount = amount - ? WHERE iban = ?");
	   update1.setObject(1, amount);
	   update1.execute();
	   PreparedStatement update2 = dataSource.getConnection().prepareStatement("UPDATE account SET amount = amount - ?");
	   update1.setObject(1, ibanFrom);
	   update1.execute();
	   dataSource.getConnection().commit();
   }
}
