package com.nevexis;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Serializer {

	public String Serialize(Object object) throws Exception {
		if (Objects.isNull(object)) {
			throw new Exception("The object to serialize is null");
		} // is it null
		Map<String, String> elements = getStringsInMap(object);
		String json = getJson(elements);
		System.out.println(json);
		return json;
	}

	// get map of elements value and key
	private Map<String, String> getStringsInMap(Object object) throws Exception {
		Class<?> clazz = object.getClass();
		Map<String, String> elementsMap = new HashMap<>();
		for (Field field : clazz.getDeclaredFields()) {// minavame prez vseki field
			field.setAccessible(true);

			if (field.isAnnotationPresent(MyAnnotation.class)) {// vseki element da ima anotacia
				elementsMap.put(getAnnotation(field), (String) field.get(object).toString());
			}
		}
		return elementsMap;

	}

	// get json
	public String getJson(Map<String, String> jsonMap) {
		String jsonString = jsonMap.entrySet().stream()
				.map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
				.collect(Collectors.joining(","));
		return "{" + jsonString + "}";
	}

	private String getAnnotation(Field field) {
		String annotation = field.getAnnotation(MyAnnotation.class).value();
		if (annotation.isEmpty()) {

			return field.getName();
		} else {
			return annotation;
		}
	}

	public String getAnnotations(Object object) {
		String result ="";
		for (Field field : object.getClass().getDeclaredFields()) {// minavame prez vseki field
			field.setAccessible(true);

			if (field.isAnnotationPresent(MyAnnotation.class)) {// vseki element da ima anotacia
				result+=field.getAnnotation(MyAnnotation.class).value();
				result+=" ";
			}
		}
		return result;
	}

}
