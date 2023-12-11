package edu.hw10;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Task1Annotations {
    @Target(value= ElementType.PARAMETER)
    @Retention(value= RetentionPolicy.RUNTIME)
    public @interface NotNull {

    }

    @Target(value= ElementType.PARAMETER)
    @Retention(value= RetentionPolicy.RUNTIME)
    public @interface Max {
        int value();
    }

    @Target(value= ElementType.PARAMETER)
    @Retention(value= RetentionPolicy.RUNTIME)
    public @interface Min {
        int value();
    }

    @Target(value= ElementType.METHOD)
    @Retention(value= RetentionPolicy.RUNTIME)
    public @interface RandomGenerator {
        String typeName();
    }
}
