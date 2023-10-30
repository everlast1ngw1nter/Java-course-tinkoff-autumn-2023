package edu.project2;

import java.awt.Point;
import java.util.Random;

public class BinaryTreeGenerator implements MazeGenerator {
    private final int width;
    private final int height;
    private final int[][] maze;
    private final Random random;

    private final Cell start = new Cell(0, 0, CellType.EMPTY);

    private Cell end = null;

    private static final Point[] DIRECTIONS = new Point[] {
            new Point(-1, 0), new Point(0, 1),
    };

    public BinaryTreeGenerator(int height, int width) {
        this.width = width;
        this.height = height;
        this.maze = new int[height][width];
        this.random = new Random();
    }

    private void initMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = 1;
            }
        }
    }

    private void getEndCell() {
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

    private Maze convertToMaze() {
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
        return new Maze(cellMaze, start, end, height, width);
    }

    public Maze generateMaze() {
        initMaze();
        maze[0][0] = 0;
        for (int i = height - 1; i > 0; i = i - 2) {
            for (int j = 0; j < width; j++) {
                var rndPoint = DIRECTIONS[random.nextInt(0, 2)];
                var nextPoint = new Point(rndPoint.x + i, rndPoint.y + j);
                if (!isInBounds(nextPoint)) {
                    nextPoint = new Point(-rndPoint.y + i, -rndPoint.x + j);
                }
                maze[i - 1][j] = 0;
                maze[nextPoint.x][nextPoint.y] = 0;
            }
        }
        getEndCell();
        return convertToMaze();
    }

    private boolean isInBounds(Point p) {
        return p.x < height && p.x > -1
                && p.y < width && p.y > -1;
    }
}
