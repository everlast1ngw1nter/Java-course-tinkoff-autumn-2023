package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.jar.asm.Opcodes.*;

public class Task3 {

    public static long generateNewClass()
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> newClass = new ByteBuddy()
                .subclass(Object.class)
                .name("FibCalculator")
                .defineMethod("fib", long.class, Visibility.PUBLIC)
                .withParameters(int.class)
                .intercept(new SumImplementation())
                .make()
                .load(Task3.class.getClassLoader())
                .getLoaded();
        var obj = newClass.getConstructor().newInstance();
        var meth = newClass.getMethod("fib", int.class);

        return (long) meth.invoke(obj, 4);
    }

    public static class SumImplementation implements Implementation {

        @Override
        public InstrumentedType prepare(InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override
        public ByteCodeAppender appender(Target implementationTarget) {
            return new SumMethod();
        }
    }

    public static class SumMethod implements ByteCodeAppender {
        @Override
        public Size apply(MethodVisitor mv, Implementation.Context context,
                          MethodDescription methodDescription) {
            if (!methodDescription.getReturnType().asErasure().represents(long.class)) {
                throw new IllegalArgumentException(methodDescription + " must return int");
            }
            // long занимает 2 слота
            mv.visitCode();
            mv.visitInsn(Opcodes.LCONST_1);
            mv.visitVarInsn(Opcodes.LSTORE, 0);
            mv.visitInsn(Opcodes.LCONST_1);
            mv.visitVarInsn(Opcodes.LSTORE, 2);
            mv.visitVarInsn(Opcodes.LLOAD, 0);
            mv.visitVarInsn(Opcodes.LLOAD, 2);
            mv.visitInsn(Opcodes.LADD);
            mv.visitInsn(Opcodes.LRETURN);
            mv.visitEnd();
            return new Size( 10, 10);
        }
    }
}
