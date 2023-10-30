package edu.project2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DiggerGeneratorTest {

    @Test
    void getPathTest() {
        MazeGenerator generator = new DiggerGenerator(3, 4);
        Maze maze = generator.generateMaze();
        assertThat(maze.start())
                .isEqualTo(new Cell(0,0, CellType.EMPTY));
        assertThat(maze.end().type())
                .isEqualTo(CellType.EMPTY);
    }
}

