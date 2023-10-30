package edu.project2;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

public class BFSPathFinderTest {

    @Test
    void getPathTest() {
        Cell[][] grid = new Cell[][] {
                new Cell[] {new Cell(0, 0, CellType.EMPTY),
                        new Cell(0, 1, CellType.EMPTY)},
                new Cell[] {new Cell(1, 0, CellType.EMPTY),
                        new Cell(1, 1, CellType.EMPTY)}
        };
        Cell start = new Cell(0, 0, CellType.EMPTY);
        Cell end = new Cell(1, 1, CellType.EMPTY);
        Maze maze = new Maze(grid, start, end, 2, 2);
        PathFinder pathFinder = new BFSPathFinder(maze);
        List<Cell> path = pathFinder.getPath();
        assertThat(path.size())
                .isEqualTo(3);
    }

    @Test
    void startIsEndPathTest() {
        Cell[][] grid = new Cell[][] {
                new Cell[] {new Cell(0, 0, CellType.EMPTY)}
        };
        Cell start = new Cell(0, 0, CellType.EMPTY);
        Cell end = new Cell(0, 0, CellType.EMPTY);
        Maze maze = new Maze(grid, start, end, 1, 1);
        PathFinder pathFinder = new BFSPathFinder(maze);
        List<Cell> path = pathFinder.getPath();
        assertThat(path.size())
                .isEqualTo(1);
    }

    @Test
    void noPathTest() {
        Cell[][] grid = new Cell[][] {
                new Cell[] {new Cell(0, 0, CellType.EMPTY),
                        new Cell(0, 1, CellType.WALL)},
                new Cell[] {new Cell(1, 0, CellType.WALL),
                        new Cell(1, 1, CellType.EMPTY)}
        };
        Cell start = new Cell(0, 0, CellType.EMPTY);
        Cell end = new Cell(1, 1, CellType.EMPTY);
        Maze maze = new Maze(grid, start, end, 2, 2);
        PathFinder pathFinder = new BFSPathFinder(maze);
        List<Cell> path = pathFinder.getPath();
        assertThat(path)
                .isNull();
    }
}
