package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.Map;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

class Task3Test {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(new String[] {"a", "bb", "a", "bb"},
                        Map.of("bb", 2, "a", 2)),
                Arguments.of(new String[] {"this", "and", "that", "and"},
                        Map.of("that", 1,"and", 2, "this", 1)),
                Arguments.of(new String[] {"код", "код", "код", "bug"},
                        Map.of("код", 3, "bug", 1)),
                Arguments.of(new Integer[] {1, 1, 2, 2},
                        Map.of(1, 2, 2, 2)),
                Arguments.of(new Integer[] {},
                        Map.of())
        );
    }
    @ParameterizedTest
    @MethodSource("generateData")
    <T> void freqDictTest(T[] input, Map<T, Integer> expected) {
        assertThat(Task3.freqDict(input))
                .isEqualTo(expected);
    }
}