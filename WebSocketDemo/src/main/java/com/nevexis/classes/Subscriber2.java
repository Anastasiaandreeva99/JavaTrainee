package com.nevexis.classes;

public class Subscriber2 implements SubscriberInterface {
	@Override
	public void send() {
        System.out.println("Subscriber2 invoked when enter!");		
	}
}
