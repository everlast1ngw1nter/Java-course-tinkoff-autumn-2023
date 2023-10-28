package edu.project2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var maze = new BinaryTreeGenerator(15, 13).generateMaze();
        var bfsPathFinder = new BFSPathFinder(maze).getPath();
        var consolePrint1 = new PrettyConsolePrint(maze, bfsPathFinder);
        consolePrint1.print();

        System.out.println();
//
//        var dfsPathFinder = new DFSPathFinder(maze).getPath();
//        var consolePrint2 = new PrettyConsolePrint(maze, dfsPathFinder);
//        consolePrint2.print();
    }
}
