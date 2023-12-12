package edu.hw10;

import org.junit.jupiter.api.Test;
import edu.hw10.Task2CacheAnnotation.Cache;

public class Task2Tests {

    @Test
    void proxyTest() {
        FibCalculator inst = new RealFib();
        FibCalculator proxy = Task2CacheProxy.create(inst);
        for (var i = 0; i < 10; i++) {
            proxy.fib(12, "a");
        }
        var a = 1 + 1;
    }

    public interface FibCalculator {
        @Cache(persist = false)
        long fib(int number, String str);
    }

    public static class RealFib implements FibCalculator {
        @Override
        public long fib(int number, String str) {
            return 0;
        }
    }
}
