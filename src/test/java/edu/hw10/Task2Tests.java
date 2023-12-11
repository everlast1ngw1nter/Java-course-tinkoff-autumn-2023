package edu.hw10;

import org.junit.jupiter.api.Test;
import edu.hw10.Task2CacheAnnotation.Cache;

public class Task2Tests {

    @Test
    void proxyTest() {
        FibCalculator inst = new RealFib();
        FibCalculator proxy = Task2CacheProxy.create(inst);
        proxy.fib(12);
        var a = 1 + 1;
    }

    public interface FibCalculator {
        @Cache(persist = true)
        long fib(int number);
    }

    public static class RealFib implements FibCalculator {
        @Override
        public long fib(int number) {
            return 0;
        }
    }
}
