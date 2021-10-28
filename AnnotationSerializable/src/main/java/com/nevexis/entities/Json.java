package com.nevexis.entities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Json {
	 enum JsonType
	 {
	 	SISI,KAKA,MAMA
	 }
	 public JsonType value2() default JsonType.MAMA;
	 
	public String value() default "";
}
	

