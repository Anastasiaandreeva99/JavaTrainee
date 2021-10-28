package com.nevexis.classes;

public class InterceptorInitializer {

	public void init(InterceptorInterface... interceptors) {
		for (int i = 0; i < interceptors.length; i++) {
			if (i < interceptors.length - 1) {

				interceptors[i].setNext(interceptors[i + 1]);
			}
		}
	}
	
			
}
//InterceptorInitializer interceptor = new InterceptorInitializer();
//InterceptorInterface int1 = new InterceptorC();
//InterceptorInterface int2 = new InterceptorB();
//InterceptorInterface int3 = new InterceptorA();
//
//interceptor.init(int1,int2,int3);
//int1.addPurchase(3, 300, 0);