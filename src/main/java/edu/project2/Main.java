package edu.project2;

import edu.project2.finders.BFSPathFinder;
import edu.project2.finders.PathFinder;
import edu.project2.generators.BinaryTreeGenerator;
import edu.project2.generators.DiggerGenerator;

public class Main {
    public static void main(String[] args) {
        var generator = new BinaryTreeGenerator(6, 6);
        var maze = generator.generateMaze();
        var finder = new BFSPathFinder(maze);
        var print = new PrettyConsolePrint(maze, finder.getPath());
        print.print();
    }
}
