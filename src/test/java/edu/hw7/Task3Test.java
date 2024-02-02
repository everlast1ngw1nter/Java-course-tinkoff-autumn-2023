package edu.hw7;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void synchronizedMultiThreadOnlyNotNullAvailableTest() throws InterruptedException {
        var database = new Task3.SynchronizedPersonDatabase();
        Runnable writer = () -> {
            database.add(new Person(1, null, null, null));
            database.add(new Person(2, "name2", "address2", "number2"));
            database.add(new Person(1, "name1", "address2", "number1"));
        };
        Runnable reader = () -> {
            for (var i = 0; i < 5; i++) {
                var byAddress = database.findByAddress("address2");
                for (var person : byAddress) {
                    assertTrue(database.findByName(person.name()).contains(person));
                    assertTrue(database.findByPhone(person.phoneNumber()).contains(person));
                }
            }
        };

        var writerThreads = new ArrayList<>(Stream.generate(() -> new Thread(writer))
                .limit(2)
                .toList());
        var readerThreads = Stream.generate(() -> new Thread(reader))
                .limit(8)
                .toList();
        writerThreads.addAll(readerThreads);
        for (var thread : writerThreads) {
            thread.start();
        }
        for (var thread : writerThreads) {
            thread.join();
        }
    }

    @Test
    void readWriteLockMultiThreadOnlyNotNullAvailableTest() throws InterruptedException {
        var database = new Task3AndHalf.ReadWriteLockPersonDatabase();
        Runnable writer = () -> {
            database.add(new Person(1, null, null, null));
            database.add(new Person(2, "name2", "address2", "number2"));
            database.add(new Person(1, "name1", "address2", "number1"));
        };
        Runnable reader = () -> {
            for (var i = 0; i < 5; i++) {
                var byAddress = database.findByAddress("address2");
                for (var person : byAddress) {
                    assertTrue(database.findByName(person.name()).contains(person));
                    assertTrue(database.findByPhone(person.phoneNumber()).contains(person));
                }
            }
        };

        var writerThreads = new ArrayList<>(Stream.generate(() -> new Thread(writer))
                .limit(2)
                .toList());
        var readerThreads = Stream.generate(() -> new Thread(reader))
                .limit(8)
                .toList();
        writerThreads.addAll(readerThreads);
        for (var thread : writerThreads) {
            thread.start();
        }
        for (var thread : writerThreads) {
            thread.join();
        }
    }
}
