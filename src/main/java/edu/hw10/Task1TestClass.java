package edu.hw10;

public class Task1TestClass {

    public final boolean ok;

    public final long n;

    public Task1TestClass(boolean ok1, long k) {
        ok = ok1;
        n = k;
    }

    public static Task1TestClass create(boolean ok1, long k) {
        return new Task1TestClass(ok1, k);
    }
}
