package com.becheer.donation.interfaces;


import java.lang.annotation.*;


/*
* Access
* Creator : xiaokepu
* Date : 2017-11-29
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Access {
    int[] authorities() default {0};
}