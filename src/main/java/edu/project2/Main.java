package edu.project2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var a = new DiggerGenerator(10, 10).generateMaze();
        for (var x : a.grid()) {
            System.out.println(Arrays.toString(x));
        }
        System.out.println();
        var b = new BinaryTreeGenerator(9, 9).generateMaze();
        for (var x : b.grid()) {
            System.out.println(Arrays.toString(x));
        }
    }
}
