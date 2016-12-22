/*
 * TODO Copyright
 */

package com.company.mssqlview.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by aleksey on 22/12/2016.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface EntityQuery {
    String query();

    String countQuery() default "";

    boolean simpleSelectQuery() default true;
}
