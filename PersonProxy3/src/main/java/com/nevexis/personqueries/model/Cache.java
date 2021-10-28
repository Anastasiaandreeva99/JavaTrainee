package com.nevexis.personqueries.model;

import java.util.HashMap;

public class Cache {
	private static HashMap<Integer, PersonTuple> objects = new HashMap<>();
	

	public static  HashMap<Integer, PersonTuple> getObjects() {
		return objects;
	}

	public static void addObject(Integer key , PersonTuple object) {
		objects.put(key,object);
	}
	
	public static PersonTuple getObject(Integer key) {
		return objects.get(key);
	}
	
	public static boolean containsKey(Integer key) {
		return objects.containsKey(key);
	}
	public static void remove(Integer key) {
		 objects.remove(key);
	}



}
