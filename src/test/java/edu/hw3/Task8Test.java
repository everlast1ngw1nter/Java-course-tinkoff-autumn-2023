package edu.hw3;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {

    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(List.of(3, 2, 1)),
                Arguments.of(List.of("3", "11")),
                Arguments.of(List.of(0.3, 0.42, 0.1))
        );
    }
    @ParameterizedTest
    @MethodSource("generateData")
    <T extends Comparable<T>> void  backwardIteratorTest(List<T> inputList) {
        var backwardIter = new Task8.BackwardIterator<>(inputList);
        List<T> reversed = inputList.reversed();
        assertThat(backwardIter.hasNext())
                .isTrue();
        for (var elem : reversed) {
            assertThat(backwardIter.next())
                    .isEqualTo(elem);
        }
        assertThat(backwardIter.hasNext())
                .isFalse();
        assertThrows(NoSuchElementException.class, backwardIter::next);
    }
}
