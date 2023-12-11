package edu.hw10;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args)
            throws Exception {
        var rog = new Task1RandomObjectGenerator();
        var obj1 = rog.nextObject(Task1TestRecord.class);
        var obj2 = rog.nextObject(Task1TestClass.class, "create");
        System.out.println(obj1.toString());
        System.out.println(obj2.toString());
    }
}
