package edu.hw10;

import org.junit.jupiter.api.Test;
import edu.hw10.Task2CacheAnnotation.Cache;
import org.junit.jupiter.api.Timeout;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Task2Tests {

    @Test
    @Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
    void proxyCacheTest() {
        FibCalculator inst = new RealFib();
        FibCalculator proxy = Task2CacheProxy.create(inst);
        for (var i = 0; i < 10; i++) {
            proxy.fib(12, "a");
        }
    }

    @Test
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    void proxyCachePersistTest() {
        CachedInterface inst = new RealCached();
        CachedInterface proxy = Task2CacheProxy.create(inst);
        for (var i = 0; i < 10; i++) {
            proxy.meth(12, "a");
        }
        var args = new Object[] {12, "a"};
        var hash = Arrays.hashCode(args);
        File file = Path.of(".").resolve(String.valueOf(hash)).toFile();
        if (file.exists()) {
            file.delete();
        }
    }

    public interface CachedInterface {
        @Cache(persist = true)
        long meth(int number, String str);
    }

    public static class RealCached implements CachedInterface {
        @Override
        public long meth(int number, String str) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 0;
        }
    }


    public interface FibCalculator {
        @Cache(persist = false)
        long fib(int number, String str);
    }

    public static class RealFib implements FibCalculator {
        @Override
        public long fib(int number, String str) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 0;
        }
    }
}
