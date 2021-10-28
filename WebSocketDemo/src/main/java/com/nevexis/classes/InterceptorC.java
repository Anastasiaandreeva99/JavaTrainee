package com.nevexis.classes;

import java.time.LocalTime;

public class InterceptorC extends BasicInterceptor{
	@Override
	public void addPurchase(int customer, int amount,int points) {
          next.addPurchase(customer,amount,points);	
          timeBonus(customer,amount,points);
	}

	private void timeBonus(int customer, int amount,int points) {
          if(LocalTime.now().getHour()%2!=0)
          {
        	  points+=100;
        	  System.out.println("Extra 100 points!");
          }
          else
          {
        	  System.out.println("Try again!");

          }
	}


	

}
