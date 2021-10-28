package com.nevexis.model;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Ledger {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
    private String fromIban;
    private String toIban;
    private Type type;
    private BigDecimal amount;
     public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setToIban(String toIban) {
		this.toIban = toIban;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
