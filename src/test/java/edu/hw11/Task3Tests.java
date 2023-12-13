package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.implementation.bytecode.StackManipulation;
import net.bytebuddy.implementation.bytecode.assign.Assigner;
import net.bytebuddy.implementation.bytecode.constant.IntegerConstant;
import net.bytebuddy.implementation.bytecode.constant.LongConstant;
import net.bytebuddy.implementation.bytecode.member.MethodReturn;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.junit.jupiter.api.Test;

public class Task3Tests {

    @Test
    void generateNewClass() throws InstantiationException, IllegalAccessException, NoSuchMethodException {
        Class<?> newClass = new ByteBuddy()
                .subclass(Object.class)
                .name("FibCalculator")
                .defineMethod("fib", long.class, Visibility.PUBLIC)
                .withParameters(int.class)
                .intercept(new SumImplementation())
                .make()
                .load(getClass().getClassLoader())
                .getLoaded();
        var meth = newClass.getMethod("fib", int.class);
    }

    public class SumImplementation implements Implementation {

        @Override
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override
        public ByteCodeAppender appender(Target implementationTarget) {
            return new SumMethod();
        }
    }

    public class SumMethod implements ByteCodeAppender {

        @Override
        public Size apply(MethodVisitor methodVisitor, Implementation.Context context,
                          MethodDescription methodDescription) {
            if (!methodDescription.getReturnType().asErasure().represents(long.class)) {
                throw new IllegalArgumentException(methodDescription + " must return int");
            }
            StackManipulation.Size operandStackSize = new StackManipulation.Compound(
                    LongConstant.forValue(10),
                    MethodReturn.LONG
            ).apply(methodVisitor, context);
            return new Size(operandStackSize.getMaximalSize(), methodDescription.getStackSize());
        }
    }
}
