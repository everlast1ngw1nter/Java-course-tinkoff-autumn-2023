package edu.hw3;

import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {
    static Stream<Arguments> generateData() {
        return Stream.of(
                Arguments.of(
                        List.of("F F", "A A"),
                        Order.ASC,
                        List.of(new Contact("A", "A"), new Contact("F", "F"))),
                Arguments.of(
                        List.of("F", "A A"),
                        Order.ASC,
                        List.of(new Contact("A", "A"), new Contact("F"))),
                Arguments.of(
                        List.of("A", "F F"),
                        Order.DESC,
                        List.of(new Contact("F", "F"), new Contact("A"))),
                Arguments.of(
                        List.of("John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"),
                        Order.ASC,
                        List.of(new Contact("Thomas", "Aquinas"),
                                new Contact("Rene", "Descartes"),
                                new Contact("David", "Hume"),
                                new Contact("John", "Locke"))),
                Arguments.of(
                        List.of("Paul Erdos", "Leonhard Euler", "Carl Gauss"),
                        Order.DESC,
                        List.of(new Contact("Carl", "Gauss"),
                                new Contact("Leonhard", "Euler"),
                                new Contact("Paul", "Erdos"))),
                Arguments.of(
                        new ArrayList<>(),
                        Order.DESC,
                        new ArrayList<>()),
                Arguments.of(
                        null,
                        Order.DESC,
                        new ArrayList<>())
        );
    }

    @ParameterizedTest
    @MethodSource("generateData")
    void parseContactsTest(List<String> inputNames, Order inputOrder, List<Contact> expected) {
        assertThat(Task5.parseContacts(inputNames, inputOrder))
                .isEqualTo(expected);
    }
}
