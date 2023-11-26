package edu.hw7;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    @Test
    void synchronizedSingleThreadOnlyNotNullAvailableTest() {
        var database = new Task3.SynchronizedPersonDatabase();
        database.add(new Person(1, null, "address", "number"));
        assertEquals(Collections.emptyList(), database.findByAddress("address"));
        database.add(new Person(1, "name", "address", "number"));
        assertEquals(1, database.findByAddress("address").size());
    }

    @Test
    void readWriteLockSingleThreadOnlyNotNullAvailableTest() {
        var database = new Task3AndHalf.ReadWriteLockPersonDatabase();
        database.add(new Person(1, null, "address", "number"));
        assertEquals(Collections.emptyList(), database.findByAddress("address"));
        database.add(new Person(1, "name", "address", "number"));
        assertEquals(1, database.findByAddress("address").size());
    }

    @Test
    void synchronizedMultiThreadOnlyNotNullAvailableTest() {
        var database = new Task3.SynchronizedPersonDatabase();
        database.add(new Person(1, null, "address", "number"));
        assertEquals(Collections.emptyList(), database.findByAddress("address"));
        database.add(new Person(1, "name", "address", "number"));
        assertEquals(1, database.findByAddress("address").size());
    }

    @Test
    void readWriteLockMultiThreadOnlyNotNullAvailableTest() {
        var database = new Task3AndHalf.ReadWriteLockPersonDatabase();
        database.add(new Person(1, null, "address", "number"));
        assertEquals(Collections.emptyList(), database.findByAddress("address"));
        database.add(new Person(1, "name", "address", "number"));
        assertEquals(1, database.findByAddress("address").size());
    }
}
