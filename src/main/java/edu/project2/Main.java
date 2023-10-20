package edu.project2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var a = new DiggerGenerator(10, 10).getMaze();
        for (var x : a) {
            System.out.println(Arrays.toString(x));
        }
    }
}
