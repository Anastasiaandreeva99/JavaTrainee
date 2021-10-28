package com.nevexis;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.print.DocFlavor.URL;

public class Processor2 {
  public static void getElements() throws ClassNotFoundException
  {
	  ClassLoader classLoader = Processor2.class.getClassLoader();

		String packageName = "com.nevexis";
		String packagePath = "com/nevexis/";
		java.net.URL urls = classLoader.getResource(packagePath);

		// Get all the class files in the specified URL Path.
		File folder = new File(urls.getPath());
		File[] classes = folder.listFiles();

		int size = classes.length;
		List<Class<?>> classList = new ArrayList<Class<?>>();

		for (int i = 0; i < size; i++) {
			int index = classes[i].getName().indexOf(".");
			String className = classes[i].getName().substring(0, index);
			String classNamePath = packageName + "." + className;
			Class<?> repoClass;
			repoClass = Class.forName(classNamePath);
			Annotation[] annotations = repoClass.getAnnotations();
			for (int j = 0; j < annotations.length; j++) {
				System.out.println("Annotation in class " + repoClass.getName() + " is "
						+ annotations[j].annotationType().getName());
			}
			classList.add(repoClass);
  }
}}
