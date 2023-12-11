package edu.hw10;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Task2CacheProxy {

    public static <T> T create(T proxiedInstance) {
        var loader = proxiedInstance.getClass().getClassLoader();
        var interfaces = proxiedInstance.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(loader, interfaces, new CacheInvocationHandler<>(proxiedInstance));
    }

    private static class CacheInvocationHandler <T> implements InvocationHandler {

        private final T proxiedInstance;

        public CacheInvocationHandler(T proxiedInstance) {
            this.proxiedInstance = proxiedInstance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws InvocationTargetException, IllegalAccessException {
            var ann = method.getAnnotation(Task2CacheAnnotation.Cache.class);
            if (ann != null) {
                System.out.println("Do cache");
            }
            return method.invoke(proxiedInstance, args);
        }
    }
}
