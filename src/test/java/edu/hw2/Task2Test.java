package edu.hw2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    static Arguments[] rectangles() {
        return new Arguments[]{
                Arguments.of(new Task2.Rectangle(10, 20)),
                Arguments.of(new Task2.Square(10))
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Task2.Rectangle rect) {
        Task2.Rectangle rect1 = rect.setWidth(20);
        Task2.Rectangle rect2 = rect1.setHeight(10);

        assertThat(rect2.area()).isEqualTo(200.0);
    }

    @Test
    void getRectangleTest() {
        Task2.Square square = new Task2.Square(10);
        Task2.Rectangle rec = square.setHeight(20);
        assertThat(rec.area()).isEqualTo(200.0);
        assertThat(rec.getHeight()).isEqualTo(20.0);
        assertThat(rec.getWidth()).isEqualTo(10.0);
    }

    @Test
    void squareTest() {
        Task2.Square square = new Task2.Square(10);
        assertThat(square.getHeight()).isEqualTo(10.0);
        assertThat(square.getWidth()).isEqualTo(10.0);
        assertThat(square.area()).isEqualTo(100.0);
    }
}
