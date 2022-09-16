package com.hannah.practiceb.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authorized {
    public AuthRestriction value() default AuthRestriction.LOGGED_IN;

    //Username has to be the same as the token
    //in other words, the user has to be doing the request themselves
    //this is to prevent users from modifying other users\
    public boolean requireSelfAction() default false;
}
