package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2AndTests {

    @Test
    void redefineMethodTest() {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(ArithmeticUtils.class)
                .method(named("sum")
                        .and(isDeclaredBy(ArithmeticUtils.class)))
                .intercept(MethodDelegation.to(ArithmeticByteBuddy.class))
                .make()
                .load(getClass().getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        assertEquals(ArithmeticUtils.sum(3,8), 24);

    }

    static class ArithmeticByteBuddy {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

    static class ArithmeticUtils {
        public static int sum(int a, int b) {
            return a + b;
        }
    }
}
