package edu.project2.finders;

import edu.project2.Cell;
import edu.project2.Maze;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class DFSPathFinder extends PathFinder {

    public DFSPathFinder(Maze maze) {
        super(maze, new HashMap<>());
    }

    protected boolean findPath() {
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
}
