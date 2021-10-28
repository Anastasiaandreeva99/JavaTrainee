package com.nevexis.classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Service {
	Map<String, Set<SubscriberInterface>> subscribersMap = new HashMap<String, Set<SubscriberInterface>>();
	 public void init(Map<String, Set<SubscriberInterface>> subscribersMap)
	 {
		 this.subscribersMap= subscribersMap;
	 }
	 public void function(String command)
	 {
		 Set<SubscriberInterface> subscribers = subscribersMap.get(command);
		 for(SubscriberInterface sub:subscribers)
		 {
			 sub.send();
		 }
	 }
}
