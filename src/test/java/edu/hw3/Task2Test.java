package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task2Test {
    @Test
    void clusterizeCorrectTest1() {
        String str = "()()()";
        var clusters = new String[] {"()", "()", "()"};
        assertThat(Task2.clusterize(str))
                .isEqualTo(clusters);
    }

    @Test
    void clusterizeCorrectTest2() {
        String str = "((()))";
        var clusters = new String[] {"((()))"};
        assertThat(Task2.clusterize(str))
                .isEqualTo(clusters);
    }

    @Test
    void clusterizeCorrectTest3() {
        String str = "((()))(())()()(()())";
        var clusters = new String[] {"((()))", "(())", "()", "()", "(()())"};
        assertThat(Task2.clusterize(str))
                .isEqualTo(clusters);
    }

    @Test
    void clusterizeCorrectTest4() {
        String str = "((())())(()(()()))";
        var clusters = new String[] {"((())())", "(()(()()))"};
        assertThat(Task2.clusterize(str))
                .isEqualTo(clusters);
    }

    @Test
    void clusterizeIncorrectTest1() {
        String str = "(";
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(str));
    }

    @Test
    void clusterizeIncorrectTest2() {
        String str = "()a";
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(str));
    }

    @Test
    void clusterizeIncorrectTest3() {
        String str = "()())()";
        assertThrows(IllegalArgumentException.class, () -> Task2.clusterize(str));
    }
}