package edu.project2.generators;


import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class DiggerGenerator extends MazeGenerator {

    private final ArrayList<Digger> diggers = new ArrayList<>();

    private final Random rnd;

    private Point lastPoint = new Point(0, 0);

    private static final Point[] DIRECTIONS = new Point[] {
            new Point(1, 0), new Point(0, 1),
            new Point(-1, 0), new Point(0, -1)
    };

    public DiggerGenerator(int height, int width) {
        super(width, height, new Cell(0, 0, CellType.EMPTY), null);
        rnd = new Random();
    }

    public Maze generateMaze() {
        initMaze();
        maze[0][0] = 0;
        for (int i = 0; i < height * width; i++) {
            if (i % Math.min(height, width) / 2 == 0) {
                diggers.add(new Digger(lastPoint.x, lastPoint.y));
            }
            dig(maze);
        }
        return convertToMaze();
    }

    private void initMaze() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                maze[i][j] = 1;
            }
        }
    }

    private boolean isSeparated(Point p, Point diggerPos) {
        for (Point dir : DIRECTIONS) {
            var currentPoint = new Point(dir.x + p.x, dir.y + p.y);
            if (!isInBounds(currentPoint)) {
                continue;
            }
            if (maze[currentPoint.x][currentPoint.y] == 0 && !currentPoint.equals(diggerPos)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAvailable(Point p, Point diggerPos) {
        return isInBounds(p) && isSeparated(p, diggerPos);
    }

    private void dig(int[][] maze) {
        for (Digger digger : diggers) {
            var availableCells = new ArrayList<Point>();
            for (Point dir : DIRECTIONS) {
                var currentPoint = new Point(dir.x + digger.point.x, dir.y + digger.point.y);
                if (isAvailable(currentPoint, digger.point)) {
                    availableCells.add(currentPoint);
                }
            }
            if (availableCells.isEmpty()) {
                continue;
            }
            lastPoint = availableCells.get(rnd.nextInt(availableCells.size()));
            digger.move(lastPoint);
            maze[lastPoint.x][lastPoint.y] = 0;
        }
    }

    private static class Digger {
        private Point point;

        Digger(int row, int col) {
            point = new Point(row, col);
        }

        void move(Point p) {
            point = p;
        }
    }
}
