package edu.hw9;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class Task3MultiThreadDFS {

    private static final Point[] DIRECTIONS = new Point[] {
            new Point(1, 0), new Point(0, 1),
            new Point(-1, 0), new Point(0, -1)
    };

    private final Maze maze;

    public Task3MultiThreadDFS(int[][] grid, Point start, Point end, int height, int width) {
        this.maze = createMaze(grid, new Cell(start.x, start.y, CellType.EMPTY),
                new Cell(end.x, end.y, CellType.EMPTY), height, width);
    }

    private Maze createMaze(int[][] grid, Cell start, Cell end, int height, int width) {
        var cellMaze = new Cell[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                cellMaze[i][j] = switch (grid[i][j]) {
                    case 0 -> new Cell(i, j, CellType.EMPTY);
                    case 1 -> new Cell(i, j, CellType.WALL);
                    default -> throw new IllegalArgumentException();
                };
            }
        }
        return new Maze(cellMaze, start, end, height, width);
    }


    public boolean findPathMultiThread() {
        var finder = new PathFinder(maze.start, Collections.newSetFromMap(new ConcurrentHashMap<>()));
        try (var forkJoinPool = new ForkJoinPool()) {
            return forkJoinPool.invoke(finder);
        }
    }

    private List<Cell> getNeighbours(Cell currCell) {
        var list = new ArrayList<Cell>();
        for (var elem : DIRECTIONS) {
            Point nextPoint = new Point(currCell.row() + elem.x, currCell.col() + elem.y);
            if (isInBounds(nextPoint)
                    && maze.grid()[nextPoint.x][nextPoint.y].type().equals(CellType.EMPTY)) {
                list.add(maze.grid()[nextPoint.x][nextPoint.y]);
            }
        }
        return list;
    }

    private boolean isInBounds(Point p) {
        return p.x < maze.height() && p.x > -1
                && p.y < maze.width() && p.y > -1;
    }

    private class PathFinder extends RecursiveTask<Boolean> {

        private final Set<Cell> visited;

        private final Cell curr;

        PathFinder(Cell curr, Set<Cell> visited) {
            this.curr = curr;
            this.visited = visited;
        }

        @Override
        protected Boolean compute() {
            if (curr.equals(maze.end())) {
                return true;
            }
            visited.add(curr);
            var forks = new ArrayList<ForkJoinTask<Boolean>>();
            for (var incidentNode : getNeighbours(curr)) {
                if (visited.contains(incidentNode)) {
                    continue;
                }
                forks.add(new PathFinder(incidentNode, visited).fork());
            }
            if (forks.isEmpty()) {
                return false;
            }
            return forks.stream().anyMatch(ForkJoinTask::join);
        }
    }

    private record Maze(Cell[][] grid, Cell start, Cell end, int height, int width) {

    }

    private record Cell(int row, int col, CellType type) {

    }

    private enum CellType {

        EMPTY,
        WALL
    }
}
