package com.nevexis.classes;
//package com.nevexis;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//public class Publisher {
//
//	public static void main(String[] args) {
//		SubscriberInterface subs1 = new Subscriber1();
//		SubscriberInterface subs2 = new Subscriber2();
//		SubscriberInterface subs3 = new Subscriber3();
//		
//		
//		Set<SubscriberInterface> set1 = new HashSet<SubscriberInterface>();
//		Set<SubscriberInterface> set2 = new HashSet<SubscriberInterface>();
//		
//        set1.add(subs2);
//		set1.add(subs3);
//		set2.add(subs1);
//		
//		Map<String, Set<SubscriberInterface>> subscribersMap = new HashMap<String, Set<SubscriberInterface>> ();
//		
//		subscribersMap.put("ENTER", set1);
//		subscribersMap.put("SHIFT", set2);
//		
//       Service service = new Service();
//       
//       service.init(subscribersMap);
//       service.function("ENTER");
//       service.function("SHIFT");
//	}
//
//}
