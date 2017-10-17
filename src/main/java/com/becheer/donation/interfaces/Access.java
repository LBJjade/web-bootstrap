package com.becheer.donation.interfaces;


import java.lang.annotation.*;


/*
* 
* Creator : xiaokepu
* Date : 
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {

    String value() default "";

    String authorities() default "";
}