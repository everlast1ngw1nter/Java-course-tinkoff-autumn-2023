package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.hw2.Task1.Expr.*;

class Task1Test {
    private static final double DELTA = 0.00001d;

    @Test
    void constantTest() {
        double two = new Constant(2).evaluate();
        assertEquals(2, two, DELTA);
    }

    @Test
    void negateTest1() {
        Constant two = new Constant(2);
        double minusTwo = new Negate(two).evaluate();
        assertEquals(-2, minusTwo, DELTA);
    }

    @Test
    void negateTest2() {
        Constant two = new Constant(-42);
        double minusTwo = new Negate(two).evaluate();
        assertEquals(42, minusTwo, DELTA);
    }

    @Test
    void additionTest() {
        Constant two = new Constant(2);
        Constant four = new Constant(4);
        double six = new Addition(two, four).evaluate();
        assertEquals(6, six, DELTA);
    }

    @Test
    void multiplicationTest() {
        Constant two = new Constant(2);
        Constant four = new Constant(4);
        double eight = new Multiplication(two, four).evaluate();
        assertEquals(8, eight, DELTA);
    }

    @Test
    void exponentTest() {
        Constant two = new Constant(2);
        Constant four = new Constant(4);
        double sixteen = new Exponent(two, four).evaluate();
        assertEquals(16, sixteen, DELTA);
    }

    @Test
    void complexTest() {
        Constant two = new Constant(2);
        Constant four = new Constant(4);
        Constant five = new Constant(5);
        Multiplication eight = new Multiplication(two, four);
        Negate minusFive = new Negate(five);
        Addition three = new Addition(eight, minusFive);
        double twentySeven = new Exponent(three, 3).evaluate();
        assertEquals(27, twentySeven, DELTA);
    }
}
