package edu.hw10;
import edu.hw10.Task1Annotations.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task1Tests {

    private static final Task1RandomObjectGenerator ROG = new Task1RandomObjectGenerator();

    @Test
    void recordCreating() throws Exception {
        for (var i = 0; i < 10; i++) {
            var obj = ROG.nextObject(TestRecord.class);
            assertEquals(obj.a, 2);
            assertNotNull(obj.b);
        }
    }

    @Test
    void classCreating() throws Exception {
        for (var i = 0; i < 10; i++) {
            var obj = ROG.nextObject(TestClass.class);
            assertTrue(obj.n < 0);
        }
    }

    @Test
    void classCreatingFabricMethod() throws Exception {
        for (var i = 0; i < 10; i++) {
            var obj = ROG.nextObject(TestClass.class, "create");
            assertTrue(obj.n < 10);
        }
    }

    public record TestRecord(@Max(3) @Min(2) int a, @NotNull String b) {
    }

    public static class TestClass {

        public final boolean ok;

        public final int n;

        public TestClass(boolean ok, @Max(0) int n) {
            this.ok = ok;
            this.n = n;
        }

        public static TestClass create(boolean ok, @Max(10) int n) {
            return new TestClass(ok, n);
        }
    }
}
