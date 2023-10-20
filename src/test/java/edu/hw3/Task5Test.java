package edu.hw3;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task5Test {

    @Test
    void stockMarketTest1() {
        var stockMarket = new String[] {"F F", "A A"};
        var contactArray = new Task5.Contact[] {new Task5.Contact("A", "A"), new Task5.Contact("F", "F")};
        assertThat(Task5.parseContacts(stockMarket, "ASC"))
                .isEqualTo(contactArray);
    }

    @Test
    void stockMarketTest2() {
        var stockMarket = new String[] {"F", "A A"};
        var contactArray = new Task5.Contact[] {new Task5.Contact("A", "A"), new Task5.Contact("F")};
        assertThat(Task5.parseContacts(stockMarket, "ASC"))
                .isEqualTo(contactArray);
    }

    @Test
    void stockMarketTest3() {
        var stockMarket = new String[] {"A", "F F"};
        var contactArray = new Task5.Contact[] { new Task5.Contact("F", "F"), new Task5.Contact("A")};
        assertThat(Task5.parseContacts(stockMarket, "DESC"))
                .isEqualTo(contactArray);
    }

    @Test
    void stockMarketTest4() {
        var stockMarket = new String[] {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        var contactArray = new Task5.Contact[] {
                new Task5.Contact("Thomas", "Aquinas"),
                new Task5.Contact("Rene", "Descartes"),
                new Task5.Contact("David", "Hume"),
                new Task5.Contact("John", "Locke"),
        };
        assertThat(Task5.parseContacts(stockMarket, "ASC"))
                .isEqualTo(contactArray);
    }

    @Test
    void stockMarketTest5() {
        var stockMarket = new String[] {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        var contactArray = new Task5.Contact[] {
                new Task5.Contact("Carl", "Gauss"),
                new Task5.Contact("Leonhard", "Euler"),
                new Task5.Contact("Paul", "Erdos"),
        };
        assertThat(Task5.parseContacts(stockMarket, "DESC"))
                .isEqualTo(contactArray);
    }

    @Test
    void stockMarketTest6() {

        assertThat(Task5.parseContacts(new String[] {}, "DESC"))
                .isEqualTo(new Task5.Contact[] {});
    }

    @Test
    void stockMarketTest7() {
        assertThat(Task5.parseContacts(null, "DESC"))
                .isEqualTo(new Task5.Contact[] {});
    }
}
