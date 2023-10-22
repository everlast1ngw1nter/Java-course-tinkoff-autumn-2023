package edu.hw2;

public class Task4 {

    private Task4() {
    }

    public static CallingInfo callingInfo(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[0];
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        return new CallingInfo(className, methodName);
    }

    public record CallingInfo(String className, String methodName) {}
}
