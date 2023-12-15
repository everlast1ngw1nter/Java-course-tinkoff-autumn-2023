package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2AndTests {

    @Test
    void redefineMethodTest() {
        ByteBuddyAgent.install();
        new ByteBuddy()
                .redefine(ArithmeticUtils.class)
                .method(named("sum"))
                .intercept(MethodDelegation.to(ArithmeticByteBuddy.class))
                .make()
                .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        assertEquals(new ArithmeticUtils().sum(3,8), 24);

    }

    class ArithmeticByteBuddy {
        public static int sum(int a, int b) {
            return a * b;
        }
    }

    class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }
}
