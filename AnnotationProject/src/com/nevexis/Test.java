package com.nevexis;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class Test {

	public static void main(String[] args) throws Exception {
		BigDecimal balance = new BigDecimal(10000);
		Account newAccount = new Account("BG776",balance);
		Serializer serializer = new Serializer();
		System.out.println(serializer.getAnnotations(newAccount));
		serializer.Serialize(newAccount);
		System.out.println("hey");
		
		
		

	}

}
