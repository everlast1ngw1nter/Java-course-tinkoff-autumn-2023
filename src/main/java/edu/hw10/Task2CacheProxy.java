package edu.hw10;

import edu.hw10.Task2CacheAnnotation.Cache;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Task2CacheProxy {

    public static <T> T create(T proxiedInstance) {
        var loader = proxiedInstance.getClass().getClassLoader();
        var interfaces = proxiedInstance.getClass().getInterfaces();
        return (T) Proxy.newProxyInstance(loader, interfaces, new CacheInvocationHandler<>(proxiedInstance));
    }

    private static class CacheInvocationHandler<T> implements InvocationHandler {

        private final T obj;

        private final Map<Integer, Object> cache;

        private CacheInvocationHandler(T obj) {
            this.obj = obj;
            cache = new HashMap<>();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws InvocationTargetException, IllegalAccessException, IOException {
            var ann = method.getAnnotation(Cache.class);
            if (ann == null) {
                return method.invoke(obj, args);
            }
            var cached = getIfCached(Arrays.hashCode(args));
            if (cached != null) {
                return cached;
            }
            var methRes = method.invoke(obj, args);
            cacheData(methRes, args, ann.persist());
            return methRes;
        }

        private void saveLocally(Object res, Integer hash) {
            cache.put(hash, res);
        }

        private void saveInMemory(Object res, Object[] args, Integer hash) throws IOException {
            File file = Path.of(".").resolve(String.valueOf(hash)).toFile();
            file.createNewFile();
            try (var writer = new FileWriter(file)) {
                for (var elem : args) {
                    writer.write("Arg " + elem.toString() + "\n");
                }
                writer.write("Result value " + res.toString());
                writer.flush();
            }
        }

        private Object getIfCached(Integer hash) {
            return cache.get(hash);
        }

        private void cacheData(Object res, Object[] args, boolean isPersist) throws IOException {
            var hash = Arrays.hashCode(args);
            saveLocally(res, hash);
            if (isPersist) {
                saveInMemory(res, args, hash);
            }
        }
    }
}
