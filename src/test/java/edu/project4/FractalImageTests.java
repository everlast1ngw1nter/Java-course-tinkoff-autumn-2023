package edu.project4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FractalImageTests {

    private static final FractalImage IMAGE = FractalImage.create(2, 2);

    static Stream<Arguments> generateForContainsByPoints() {
        return Stream.of(
                Arguments.of(new Point(0, 0), true),
                Arguments.of(new Point(-1, -1), false),
                Arguments.of(new Point(2, 2), false),
                Arguments.of(new Point(1.9, 1.9), true)
        );
    }

    @ParameterizedTest
    @MethodSource("generateForContainsByPoints")
    void containsByPoints(Point input, boolean expected) {
        assertEquals(expected, IMAGE.contains(input));
    }

    static Stream<Arguments> generateForContainsByInts() {
        return Stream.of(
                Arguments.of(0, 0, true),
                Arguments.of(-1, -1, false),
                Arguments.of(2, 2, false)
        );
    }

    @ParameterizedTest
    @MethodSource("generateForContainsByInts")
    void containsByInts(int n1, int n2, boolean expected) {
        assertEquals(expected, IMAGE.contains(n1, n2));
    }
}
