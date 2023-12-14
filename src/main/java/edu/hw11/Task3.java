package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import java.lang.reflect.Method;

public class Task3 {

    public static Method generateNewClass()
            throws NoSuchMethodException {
        Class<?> newClass = new ByteBuddy()
                .subclass(Object.class)
                .name("FibCalculator")
                .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
                .withParameters(int.class)
                .intercept(new FibImplementation())
                .make()
                .load(Task3.class.getClassLoader())
                .getLoaded();
        return newClass.getMethod("fib", int.class);
    }

    public static class FibImplementation implements Implementation {

        @Override
        public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override
        public @NotNull ByteCodeAppender appender(@NotNull Target implementationTarget) {
            return new Fibonacci();
        }
    }

    public static class Fibonacci implements ByteCodeAppender {
        @Override
        public @NotNull Size apply(MethodVisitor methodVisitor, Implementation.@NotNull Context context,
                                   @NotNull MethodDescription methodDescription) {
            methodVisitor.visitCode();
            Label loopStart = new Label();
            Label loopEnd = new Label();
            methodVisitor.visitInsn(Opcodes.LCONST_0);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 5);
            methodVisitor.visitIincInsn(0, -1);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitJumpInsn(Opcodes.IFEQ, loopEnd);
            methodVisitor.visitLabel(loopStart);
            methodVisitor.visitFrame(Opcodes.F_FULL, 4, new Object[]{Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG, Opcodes.LONG}, 0,  null);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 5);
            methodVisitor.visitIincInsn(0, -1);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 5);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
            methodVisitor.visitJumpInsn(Opcodes.IFNE, loopStart);
            methodVisitor.visitFrame(Opcodes.F_FULL, 4, new Object[]{Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG, Opcodes.LONG}, 0,  null);
            methodVisitor.visitLabel(loopEnd);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 5);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            methodVisitor.visitEnd();
            return new Size( 10, 10);
        }
    }
}
