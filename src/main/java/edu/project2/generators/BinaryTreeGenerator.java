package edu.project2.generators;

import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;
import java.util.Random;

public class BinaryTreeGenerator extends MazeGenerator {
    private final Random random;

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
            var downMoves = 0;
            for (int j = 0; j < width; j++) {
                maze[i - 1][j] = 0;
                if (j % 2 == 0) {
                    continue;
                }
                if (random.nextBoolean() || (j + 2 > width && downMoves == 0)) {
                    downMoves++;
                    maze[i][j - 1] = 0;
                }
            }
        }
        fillEndCell();
        return convertToMaze();
    }
}
