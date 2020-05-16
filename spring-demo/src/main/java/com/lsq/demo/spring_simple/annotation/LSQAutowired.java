package com.lsq.demo.spring_simple.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface LSQAutowired {
    String value() default "";
}
