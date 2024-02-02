package edu.project2.finders;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.ArrayDeque;
import java.util.HashSet;

public class BFSPathFinder extends PathFinder {

    public BFSPathFinder(Maze maze) {
        super(maze, new Cell[maze.width()][maze.height()]);
    }

    protected boolean findPath() {
        if (maze.start().equals(maze.end())) {
            return true;
        }
        var visited = new HashSet<Cell>();
        var queue = new ArrayDeque<Cell>();
        queue.push(maze.start());
        while (!queue.isEmpty()) {
            var node = queue.poll();
            if (visited.contains(node)) {
                continue;
            }
            visited.add(node);
            for (var incidentNode : getNeighbours(node)) {
                if (visited.contains(incidentNode)) {
                    continue;
                }
                paths[incidentNode.col()][incidentNode.row()] = node;
                if (incidentNode.equals(maze.end())) {
                    return true;
                }
                queue.add(incidentNode);
            }
        }
        return false;
    }
}
