package com.nevexis;
import java.lang.annotation.Retention;
import java.lang.reflect.*;
import java.lang.annotation.*;

import java.lang.annotation.Target;
@Retention(RetentionPolicy.RUNTIME)

public @interface MyAnnotation
{
	  public String value() default "";
}

