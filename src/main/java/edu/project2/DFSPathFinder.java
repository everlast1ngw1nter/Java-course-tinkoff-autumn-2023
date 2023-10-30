package edu.project2;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class DFSPathFinder implements PathFinder {

    private final Maze maze;

    private final HashMap<Cell, Cell> paths;

    private static final Point[] DIRECTIONS = new Point[] {
            new Point(1, 0), new Point(0, 1),
            new Point(-1, 0), new Point(0, -1)
    };

    public DFSPathFinder(Maze maze) {
        this.maze = maze;
        this.paths = new HashMap<>();
    }

    private boolean findPath() {
        if (maze.start().equals(maze.end())) {
            return true;
        }
        var visited = new HashSet<Cell>();
        var stack = new Stack<Cell>();
        stack.push(maze.start());
        while (!stack.empty()) {
            var node = stack.pop();
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            for (var incidentNode : getNeighbours(node)) {
                if (visited.contains(incidentNode)) {
                    continue;
                }
                paths.put(incidentNode, node);
                if (incidentNode.equals(maze.end())) {
                    return true;
                }
                stack.push(incidentNode);
            }
        }
        return false;
    }

    public List<Cell> getPath() {
        var list = new ArrayList<Cell>();
        if (!findPath()) {
            return null;
        }
        var currCell = maze.end();
        while (!currCell.equals(maze.start())) {
            list.add(currCell);
            currCell = paths.get(currCell);
        }
        list.add(maze.start());
        return list.reversed();
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
}
