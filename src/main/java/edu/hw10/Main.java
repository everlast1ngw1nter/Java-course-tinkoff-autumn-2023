package edu.hw10;

public class Main {
    public static void main(String[] args)
            throws Exception {
        var rog = new Task1RandomObjectGenerator();
        var obj1 = rog.nextObject(Task1TestRecord.class);
        System.out.println(obj1.toString());
    }
}
