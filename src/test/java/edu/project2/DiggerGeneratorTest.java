package edu.project2;

import edu.project2.generators.DiggerGenerator;
import edu.project2.generators.MazeGenerator;
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

