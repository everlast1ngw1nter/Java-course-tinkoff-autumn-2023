package edu.project2;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrettyConsolePrint implements PrettyPrint {

    private final Maze maze;

    private final List<Cell> path;

    private static final String WALL_SYMBOL = "⬛";

    private static final String EMPTY_SYMBOL = "⚫";

    private static final String PATH_SYMBOL = "⚪";

    private static final Logger LOGGER = LogManager.getLogger();

    public PrettyConsolePrint(Maze maze, List<Cell> path) {
        this.maze = maze;
        this.path = path;
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void print() {
        String boundWalls = WALL_SYMBOL.repeat(maze.width() + 2);
        LOGGER.info(boundWalls);
        for (var row : maze.grid()) {
            StringBuilder builder = new StringBuilder();
            builder.append(WALL_SYMBOL);
            for (var cell : row) {
                var out = switch (cell.type()) {
                    case EMPTY -> EMPTY_SYMBOL;
                    case WALL -> WALL_SYMBOL;
                };
                if (path.contains(cell)) {
                    out = PATH_SYMBOL;
                }
                builder.append(out);
            }
            builder.append(WALL_SYMBOL);
            LOGGER.info(builder);
        }
        LOGGER.info(boundWalls);
    }
}
