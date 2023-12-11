package edu.hw10;

import java.lang.reflect.Parameter;
import java.util.Arrays;

public class Task1RandomObjectGenerator {

    public <T> T nextObject(Class<T> classObj)
            throws Exception {
        var ctor = classObj.getConstructors()[0];
        var randomValues = paramsCreator(ctor.getParameters());
        return (T) ctor.newInstance(randomValues);
    }

    public <T> T nextObject(Class<T> classObj, String fabricMethodName)
            throws Exception {
        var optFabric = Arrays.stream(classObj.getDeclaredMethods())
                .filter((elem) -> elem.getName().equals(fabricMethodName))
                .findFirst();
        var fabric = optFabric.get();
        var params = fabric.getParameters();
        var randomValues = paramsCreator(params);
        return (T) fabric.invoke(classObj, randomValues);
    }

    private Object[] paramsCreator(Parameter[] params) throws Exception {
        var randomValues = new Object[params.length];
        for (var i = 0; i < params.length; i++) {
            randomValues[i] = getRandomValue(params[i]);
        }
        return randomValues;
    }

    private Object getRandomValue(Parameter parameter) throws Exception {
        var type = parameter.getType();
        return Task1RandomCreator.getRandomParameter(type.getName());
    }
}
