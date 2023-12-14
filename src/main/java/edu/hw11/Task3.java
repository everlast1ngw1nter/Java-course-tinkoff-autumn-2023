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
import java.lang.reflect.Method;
import static net.bytebuddy.jar.asm.Opcodes.*;

public class Task3 {

    public static Method generateNewClass()
            throws NoSuchMethodException {
        Class<?> newClass = new ByteBuddy()
                .subclass(Object.class)
                .name("FibCalculator")
                .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
                .withParameters(int.class)
                .intercept(new SumImplementation())
                .make()
                .load(Task3.class.getClassLoader())
                .getLoaded();
        return newClass.getMethod("fib", int.class);
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
            // 0 - this
            // 1 - int arg
            // 2+ - free space
            mv.visitCode();
            mv.visitInsn(Opcodes.LCONST_0);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
            mv.visitInsn(Opcodes.LCONST_1);
            mv.visitVarInsn(Opcodes.LSTORE, 3);
            mv.visitInsn(Opcodes.LCONST_0);
            mv.visitVarInsn(Opcodes.LSTORE, 5);
            mv.visitIincInsn(0, -1);
            Label loopStart = new Label();
            Label loopEnd = new Label();
            mv.visitLabel(loopStart);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG, Opcodes.LONG}, 0,  null);
            mv.visitVarInsn(Opcodes.LLOAD, 3);
            mv.visitVarInsn(Opcodes.LLOAD, 1);
            mv.visitInsn(Opcodes.LADD);
            mv.visitVarInsn(Opcodes.LSTORE, 5);
            mv.visitIincInsn(0, -1);
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitVarInsn(Opcodes.LLOAD, 5);
            mv.visitVarInsn(Opcodes.LLOAD, 3);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
            mv.visitVarInsn(Opcodes.LSTORE, 3);
            mv.visitJumpInsn(Opcodes.IFNE, loopStart);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG, Opcodes.LONG}, 0,  null);
            mv.visitLabel(loopEnd);
            mv.visitVarInsn(Opcodes.LLOAD, 5);
            mv.visitInsn(Opcodes.LRETURN);
            mv.visitEnd();
            return new Size( 10, 10);
        }
    }
}
