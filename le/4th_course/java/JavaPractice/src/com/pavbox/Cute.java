package com.pavbox;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cute {
    int howMuch() default 10;
}
