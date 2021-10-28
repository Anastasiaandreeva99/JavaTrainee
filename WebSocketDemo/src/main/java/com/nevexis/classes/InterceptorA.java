package com.nevexis.classes;

public abstract class InterceptorA extends BasicInterceptor{

	@Override
	public void addPurchase(int customer, int amount,int points) {
		System.out.println(String.format("Added Purchase to account %d with amount %2d", customer,amount));
	}



}
