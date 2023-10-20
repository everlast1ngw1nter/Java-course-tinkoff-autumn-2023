package edu.project2;

public class Maze {

    public final Cell[][] grid;

    public final int height;

    public final int width;

    public Maze(Cell[][] grid, int height, int width) {
        this.grid = grid;
        this.height = height;
        this.width = width;
    }
}
