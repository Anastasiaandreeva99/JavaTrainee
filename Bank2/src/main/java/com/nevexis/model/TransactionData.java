package com.nevexis.model;

import java.math.BigDecimal;

public class TransactionData {

	private String fromIban;
	private String toIban;
	private Type type;
	private BigDecimal amount;

	public TransactionData(String fromIban, String toIban, Type type, BigDecimal amount) {
		this.fromIban = fromIban;
		this.toIban = toIban;
		this.amount = amount;
	}

	public TransactionData(TransactionData _trans) {
		this.fromIban = _trans.getFromIban();
		this.toIban = _trans.getToIban();
		this.amount = _trans.getAmount();
		this.type = _trans.getType();
	}

	public String getFromIban() {
		return fromIban;
	}

	public void setFromIban(String fromIban) {
		this.fromIban = fromIban;
	}

	public String getToIban() {
		return toIban;
	}

	public Type getType() {
		return type;
	}

	public void setToIban(String toIban) {
		this.toIban = toIban;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public void setType(Type type) {
		this.type = type;
	}

}
