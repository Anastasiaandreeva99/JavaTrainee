package com.nevexis.classes;

public class InterceptorB extends BasicInterceptor{

		@Override
		public void addPurchase(int customer, int amount,int points) {
			next.addPurchase(customer, amount,points);
			promotionPoints(customer,amount,points);
			
		}

		@Override
		public InterceptorInterface getNext() {
			return next;
		}

		@Override
		public void setNext(InterceptorInterface next) {
			this.next=next;
		}

	public void promotionPoints(int customer,int amount,int points)
	{
		
	     points=points+(10/customer)*amount;
	    System.out.println(String.format("New Points earned %d", points));
	    
	}
}
