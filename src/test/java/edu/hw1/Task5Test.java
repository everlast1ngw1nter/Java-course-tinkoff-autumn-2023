package edu.hw1;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    @Test
    void palindromes() {
        assertThat(Task5.isPalindromeDescendant(11))
            .isTrue();
        assertThat(Task5.isPalindromeDescendant(11211230))
            .isTrue();
        assertThat(Task5.isPalindromeDescendant(13001120))
            .isTrue();
        assertThat(Task5.isPalindromeDescendant(23336014))
            .isTrue();
        assertThat(Task5.isPalindromeDescendant(123312))
            .isTrue();
        assertThat(Task5.isPalindromeDescendant(111))
            .isTrue();
    }

    @Test
    void notPalindromes() {
        assertThat(Task5.isPalindromeDescendant(1))
            .isFalse();
        assertThat(Task5.isPalindromeDescendant(223))
            .isFalse();
        assertThat(Task5.isPalindromeDescendant(778899))
            .isFalse();
    }
}
