package com.nevexis.classes;

import org.springframework.transaction.annotation.Transactional;


public class SynchronizedClass {
	private String name;
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	public void appendBeforeName()
	{
		synchronized(lock1)
		{
			name = "MR"+name;
			System.out.print(name);
		}
	}
	public void appendAfterName()
	{
		synchronized(lock2)
		{
			name=name+"!";
			System.out.print(name);
		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
