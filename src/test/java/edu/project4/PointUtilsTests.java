package edu.project4;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointUtilsTests {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(new Point(0, 0), 1.2, new Point(0, 0)),
                Arguments.of(new Point(1, 1), Math.PI, new Point(-1, -1))
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void rotateTest(Point input, double rotation, Point expected) {
        var res = PointUtils.rotate(input, rotation);
        assertEquals(expected.x(), res.x(), 0.001);
        assertEquals(expected.y(), res.y(), 0.001);
    }
}
