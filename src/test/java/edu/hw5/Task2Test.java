package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {

    private static Stream<Arguments> getData() {
        return Stream.of(
                Arguments.of(
                        1925,
                        List.of(
                                LocalDate.of(1925,2,13),
                                LocalDate.of(1925,3,13),
                                LocalDate.of(1925,11,13)
                        )),
                Arguments.of(
                        2024,
                        List.of(
                                LocalDate.of(2024,9,13),
                                LocalDate.of(2024,12,13)
                        )),
                Arguments.of(
                        1989,
                        List.of(
                                LocalDate.of(1989,1,13),
                                LocalDate.of(1989,10,13)
                        ))
        );
    }

    @ParameterizedTest
    @MethodSource("getData")
    void findAllFridayThirteenThisYearTest(int year, List<LocalDate> expected) {
        assertThat(Task2.findAllFridayThirteenThisYear(year))
                .isEqualTo(expected);
    }
}
