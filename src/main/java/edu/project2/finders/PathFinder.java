package edu.project2.finders;

import edu.project2.Cell;
import edu.project2.CellType;
import edu.project2.Maze;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class PathFinder {

    protected final Maze maze;

    protected final Cell[][] paths;

    protected static final Point[] DIRECTIONS = new Point[] {
            new Point(1, 0), new Point(0, 1),
            new Point(-1, 0), new Point(0, -1)
    };


    protected PathFinder(Maze maze, Cell[][] paths) {
        this.maze = maze;
        this.paths = paths;
    }

    protected abstract boolean findPath();

    public List<Cell> getPath() {
        var list = new ArrayList<Cell>();
        if (!findPath()) {
            return null;
        }
        var currCell = maze.end();
        while (!currCell.equals(maze.start())) {
            list.add(currCell);
            currCell = paths[currCell.col()][currCell.row()];
        }
        list.add(maze.start());
        return list.reversed();
    }

    protected boolean isInBounds(Point p) {
        return p.x < maze.height() && p.x > -1
                && p.y < maze.width() && p.y > -1;
    }

    protected List<Cell> getNeighbours(Cell currCell) {
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
}
