package edu.hw10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class  Task2CacheProxy<T> implements InvocationHandler {

    private final T proxiedClass;

    public Task2CacheProxy(T proxiedClass) {
        this.proxiedClass = proxiedClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        var ann = method.getAnnotation(Task2CacheAnnotation.Cache.class);
        return null;
    }
}
