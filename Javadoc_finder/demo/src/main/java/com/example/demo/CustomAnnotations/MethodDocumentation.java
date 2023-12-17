package com.example.demo.CustomAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Retention(RetentionPolicy.SOURCE)
//annotation should only be retained during the source code compilation
@Target(ElementType.METHOD)
//ElementType.METHOD refers that it can be used on methods
public @interface MethodDocumentation {


}