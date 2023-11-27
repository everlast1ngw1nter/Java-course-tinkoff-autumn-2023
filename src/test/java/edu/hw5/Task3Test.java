package edu.hw5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    private static Stream<Arguments> getParseData() {
        return Stream.of(
                Arguments.of(
                        "2020-10-10",
                        LocalDate.of(2020,10,10)
                        ),
                Arguments.of(
                        "202-12-12",
                        LocalDate.of(202,12,12)
                ),
                Arguments.of(
                        "1/3/1976",
                        LocalDate.of(1976,3,1)
                ),
                Arguments.of(
                        "1/3/19",
                        LocalDate.of(19,3,1)
                ),
                Arguments.of(
                        "1 day ago",
                        LocalDate.ofInstant(Instant.now().minus(1, ChronoUnit.DAYS),
                                ZoneId.systemDefault())
                ),
                Arguments.of(
                        "2234 days ago",
                        LocalDate.ofInstant(Instant.now().minus(2234, ChronoUnit.DAYS),
                                ZoneId.systemDefault())
                ),
                Arguments.of(
                        "tomorrow",
                        LocalDate.ofInstant(Instant.now().plus(1, ChronoUnit.DAYS),
                                ZoneId.systemDefault())
                ),
                Arguments.of(
                        "today",
                        LocalDate.ofInstant(Instant.now(),
                                ZoneId.systemDefault())
                ),
                Arguments.of(
                        "yesterday",
                        LocalDate.ofInstant(Instant.now().minus(1, ChronoUnit.DAYS),
                                ZoneId.systemDefault())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("getParseData")
    void parseDateTest(String input, LocalDate expected)
            throws ParseException {
        var localDate = Task3.parseDate(input);
        assertTrue(localDate.isPresent());
        assertThat(localDate.get())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> getIncorrectDateStringData() {
        return Stream.of(
                Arguments.of("202010-10"),
                Arguments.of("202-1212"),
                Arguments.of("13976"),
                Arguments.of("1/3"),
                Arguments.of("day ago"),
                Arguments.of("2234 dayssss ago"),
                Arguments.of("after tomorrow")
        );
    }

    @ParameterizedTest
    @MethodSource("getIncorrectDateStringData")
    void incorrectDateStringTest(String input)
            throws ParseException {
        var localDate = Task3.parseDate(input);
        assertFalse(localDate.isPresent());
    }
}
