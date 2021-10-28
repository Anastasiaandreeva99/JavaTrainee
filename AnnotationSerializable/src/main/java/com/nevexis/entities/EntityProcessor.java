package com.nevexis.entities;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityProcessor {
	
	 public String serialize(Object object) throws Exception  {

	            Class<?> objectClass = object.getClass();
	            if(null==objectClass)throw new Exception();
	            
	            Map<String, String> jsonElements = new HashMap<String, String>();

	            for (Field field: objectClass.getDeclaredFields()) {
	                field.setAccessible(true);
	                if (field.isAnnotationPresent(Json.class)) {
	                    jsonElements.put(getSerializedKey(field), (String) field.get(object));
	                }
	            }
	            System.out.println(toJsonString(jsonElements));
	            return toJsonString(jsonElements);       
	       
	    }

	    private String toJsonString(Map<String, String> jsonMap) {
	        String elementsString = jsonMap.entrySet()
	                .stream()
	                .map(entry -> "\""  + entry.getKey() + "\":\"" + entry.getValue() + "\"")
	                .collect(Collectors.joining(","));
	        return "{" + elementsString + "}";
	    }

	    private static String getSerializedKey(Field field) {
	        String annotationValue = field.getAnnotation(Json.class).value();

	        if (annotationValue.isEmpty()) {
	            return field.getName();
	        }
	        else {
	            return annotationValue;
	        }
	    }
}
