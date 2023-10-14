package edu.hw2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task1Test {
    private static final double DELTA = 0.00001d;

    @Test
    void constantTest() {
        double two = new Task1.Expr.Constant(2).evaluate();
        assertEquals(2, two, DELTA);
    }

    @Test
    void negateTest1() {
        Task1.Expr.Constant two = new Task1.Expr.Constant(2);
        double minusTwo = new Task1.Expr.Negate(two).evaluate();
        assertEquals(-2, minusTwo, DELTA);
    }

    @Test
    void negateTest2() {
        Task1.Expr.Constant two = new Task1.Expr.Constant(-42);
        double minusTwo = new Task1.Expr.Negate(two).evaluate();
        assertEquals(42, minusTwo, DELTA);
    }

    @Test
    void additionTest() {
        Task1.Expr.Constant two = new Task1.Expr.Constant(2);
        Task1.Expr.Constant four = new Task1.Expr.Constant(4);
        double six = new Task1.Expr.Addition(two, four).evaluate();
        assertEquals(6, six, DELTA);
    }

    @Test
    void multiplicationTest() {
        Task1.Expr.Constant two = new Task1.Expr.Constant(2);
        Task1.Expr.Constant four = new Task1.Expr.Constant(4);
        double eight = new Task1.Expr.Multiplication(two, four).evaluate();
        assertEquals(8, eight, DELTA);
    }

    @Test
    void exponentTest() {
        Task1.Expr.Constant two = new Task1.Expr.Constant(2);
        Task1.Expr.Constant four = new Task1.Expr.Constant(4);
        double sixteen = new Task1.Expr.Exponent(two, four).evaluate();
        assertEquals(16, sixteen, DELTA);
    }

    @Test
    void complexTest() {
        Task1.Expr.Constant two = new Task1.Expr.Constant(2);
        Task1.Expr.Constant four = new Task1.Expr.Constant(4);
        Task1.Expr.Constant five = new Task1.Expr.Constant(5);
        Task1.Expr.Multiplication eight = new Task1.Expr.Multiplication(two, four);
        Task1.Expr.Negate minusFive = new Task1.Expr.Negate(five);
        Task1.Expr.Addition three = new Task1.Expr.Addition(eight, minusFive);
        double twentySeven = new Task1.Expr.Exponent(three, 3).evaluate();
        assertEquals(27, twentySeven, DELTA);
    }
}
