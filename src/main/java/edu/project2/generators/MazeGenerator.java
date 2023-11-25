package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;
import java.awt.Point;

public abstract class MazeGenerator {

    protected final int width;
    protected final int height;

    protected final int[][] maze;

    protected final Cell start;

    protected Cell end;

    protected MazeGenerator(int width, int height, Cell start, Cell end) {
        this.width = width;
        this.height = height;
        this.maze = new int[width][height];
        this.start = start;
        this.end = end;
    }

    public abstract Maze generateMaze();

    protected Maze convertToMaze() {
        var cellMaze = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cellMaze[i][j] = switch (maze[i][j]) {
                    case 0 -> new Cell(i, j, CellType.EMPTY);
                    case 1 -> new Cell(i, j, CellType.WALL);
                    default -> throw new IllegalArgumentException();
                };
            }
        }
        fillEndCell();
        return new Maze(cellMaze, start, end, height, width);
    }

    protected void fillEndCell() {
        for (int i = 0; i < height; i++) {
            if (width - 1 - i < 0) {
                continue;
            }
            if (maze[height - 1][width - 1 - i] == 0 && end == null) {
                end = new Cell(height - 1, width - 1 - i, CellType.EMPTY);
            }
        }
        for (int i = 0; i < width; i++) {
            if (height - 1 - i < 0) {
                continue;
            }
            if (maze[height - 1 - i][width - 1] == 0 && end == null) {
                end = new Cell(height - 1 - i, width - 1, CellType.EMPTY);
            }
        }
    }

    protected boolean isInBounds(Point p) {
        return p.x < height && p.x > -1
                && p.y < width && p.y > -1;
    }
}
