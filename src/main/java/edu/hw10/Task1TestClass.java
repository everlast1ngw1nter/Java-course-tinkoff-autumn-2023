package edu.hw10;
import edu.hw10.Task1Annotations.*;

public class Task1TestClass {

    public final boolean ok;

    public final int n;

    public Task1TestClass(boolean ok1, int k) {
        ok = ok1;
        n = k;
    }

    public static Task1TestClass create(boolean ok1, int k) {
        return new Task1TestClass(ok1, k);
    }
}
