package com.nevexis;
import java.math.BigDecimal;

public class Account {
	@MyAnnotation("iban")
	private String id;
	@MyAnnotation("balance")
    private BigDecimal money;
	public Account (String _id,BigDecimal _money)
	{
		id = _id;
		money=_money;
	}
	public String getId()
	{
		return id;
	}
	public BigDecimal getAccount()
	{
		return money;
	}

}
