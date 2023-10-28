package edu.project2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var b = new BinaryTreeGenerator(9, 13).generateMaze();
        for (var x : b.grid()) {
            System.out.println(Arrays.toString(x));
        }
        var pathFinder = new BFSPathFinder(b);
        var isFound = pathFinder.getPath();
        var consolePrint = new PrettyConsolePrint(b, isFound);
        consolePrint.print();
    }
}
