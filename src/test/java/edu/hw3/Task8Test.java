package edu.hw3;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Task8Test {

    @Test
    void backwardIteratorIntTest1() {
        var backwardIter = new Task8.BackwardIterator<>(List.of(3, 2, 1));
        assertThat(backwardIter.next())
                .isEqualTo(1);
        assertThat(backwardIter.next())
                .isEqualTo(2);
        assertThat(backwardIter.next())
                .isEqualTo(3);
    }

    @Test
    void backwardIteratorIntTest2() {
        var backwardIter = new Task8.BackwardIterator<>(List.of(1));
        assertThat(backwardIter.hasNext())
                .isTrue();
        assertThat(backwardIter.next())
                .isEqualTo(1);
        assertThat(backwardIter.hasNext())
                .isFalse();
        assertThrows(NoSuchElementException.class, backwardIter::next);
    }

    @Test
    void backwardIteratorStringTest1() {
        var backwardIter = new Task8.BackwardIterator<>(List.of("3", "11"));
        assertThat(backwardIter.next())
                .isEqualTo("11");
        assertThat(backwardIter.next())
                .isEqualTo("3");
    }
}
