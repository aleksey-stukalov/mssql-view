/*
 * TODO Copyright
 */

package com.company.mssqlview.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by aleksey on 22/12/2016.
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface QueryField {
    String name();
}
