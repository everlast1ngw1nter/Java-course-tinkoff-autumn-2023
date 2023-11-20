package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;
import java.awt.Point;
import java.util.Random;

public class BinaryTreeGenerator extends MazeGenerator {
    private final Random random;

    private static final Point[] DIRECTIONS = new Point[] {
            new Point(-1, 0), new Point(0, 1),
    };

    public BinaryTreeGenerator(int height, int width) {
        super(width, height, new Cell(0, 0, CellType.EMPTY), null);
        this.random = new Random();
    }

    private void initMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = 1;
            }
        }
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
}
