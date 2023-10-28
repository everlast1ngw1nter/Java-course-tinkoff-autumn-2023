package edu.project2;

import java.util.List;

public class PrettyConsolePrint implements  PrettyPrint{

    private final Maze maze;

    private final List<Cell> path;

    private static final String WALL_SYMBOL = "⬛";

    private static final String EMPTY_SYMBOL = "⚫";

    private static final String PATH_SYMBOL = "⚪";

    public PrettyConsolePrint(Maze maze, List<Cell> path) {
        this.maze = maze;
        this.path = path;
    }

    public void print() {
        String bound_walls = WALL_SYMBOL.repeat(maze.width() + 2);
        System.out.println(bound_walls);
        for (var row : maze.grid()) {
            System.out.print(WALL_SYMBOL);
            for (var cell : row) {
                var out = switch (cell.type()) {
                    case EMPTY -> EMPTY_SYMBOL;
                    case WALL -> WALL_SYMBOL;
                };
                if (path.contains(cell)) {
                    out = PATH_SYMBOL;
                }
                System.out.print(out);
            }
            System.out.print(WALL_SYMBOL);
            System.out.println();
        }
        System.out.print(bound_walls);
    }
}
