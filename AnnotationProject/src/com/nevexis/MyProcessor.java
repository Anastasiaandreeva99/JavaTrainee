package com.nevexis;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

public class MyProcessor extends AbstractProcessor {
//	private Messager messager;
//	private Types typeUtils;
//	private Elements elementUtils;
//
//	@Override
//	public synchronized void init(ProcessingEnvironment processingEnv) {
//	    messager = processingEnv.getMessager();
//	    typeUtils = (Types) processingEnv.getTypeUtils();
//	    elementUtils = processingEnv.getElementUtils();
//	}
//	@Override
//	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
//		for (TypeElement annotation : annotations) {
//			for (Element element : roundEnv.getElementsAnnotatedWith(MyAnnotation.class)) {
//				TypeElement typeElement = elementUtils.getTypeElement(element.asType().toString());
//				Set<? extends Element> fields = typeElement.getEnclosedElements()
//			            .stream()
//			            .filter(o -> o.getKind().isField())
//			            .collect(Collectors.toSet());
//				for(Element s:fields)
//				{
//					System.out.println(s.toString());
//				}
//			}
//		}
//		return true;
//	}
//

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		annotations.forEach(annotation -> {                                
		      Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(MyAnnotation.class); 
		      elements.stream()
		        .filter(TypeElement.class::isInstance)                          
		        .map(TypeElement.class::cast)                                  
		        .map(TypeElement::getQualifiedName)                             
		        .map(name -> "Class " + name + " is annotated with " + annotation.getQualifiedName())
		        .forEach(System.out::println);
		    });
		    return true;
	}
	 

}
