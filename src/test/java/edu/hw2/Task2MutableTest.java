package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edu.hw2.Task2Mutable.*;

public class Task2MutableTest {

    @Test
    void squareTest1() {
        Square square = new Square(10);
        square.setSide(20);
        square.setHeight(30);
        assertThat(square.area()).isEqualTo(900.0);
    }
}
