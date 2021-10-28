package com.nevexis.classes;

public class Subscriber1 implements SubscriberInterface {

	@Override
	public void send() {
        System.out.println("Subscriber1 invoked when shift!");		
	}

}
