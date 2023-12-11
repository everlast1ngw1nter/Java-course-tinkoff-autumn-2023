package edu.hw10;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Task2CacheAnnotation {

    @Target(value = ElementType.PARAMETER)
    @Retention(value = RetentionPolicy.RUNTIME)
    public @interface Cache {
        boolean persist();
    }
}
