package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of("()()()", Arrays.asList("()", "()", "()")),
                Arguments.of("((()))", List.of("((()))")),
                Arguments.of("((()))(())()()(()())",
                        Arrays.asList("((()))", "(())", "()", "()", "(()())")),
                Arguments.of("((())())(()(()()))", List.of("((())())", "(()(()()))"))
        );
    }
    @ParameterizedTest
    @MethodSource("generateData")
    void clusterizeTest(String input, List<String> expected) {
        assertThat(Task2.clusterize(input))
                .isEqualTo(expected);
    }


    @ParameterizedTest
    @ValueSource(strings = {"(", "()())()", "()()()(()"})
    void notClosedOrNotOpenedParenthesesTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(input));
    }

    @ParameterizedTest
    @ValueSource(strings = {"()a", "aafa", "[]"})
    void unidentifiedSymbolsTest(String input) {
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(input));
    }
}