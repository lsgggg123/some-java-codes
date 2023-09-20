package com.lsgggg123.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * javap -v simple/target/classes/com/lsgggg123/generic/Wrapper
 *
 * output:
 * Signature: #56 // <T:Ljava/lang/Object;>Ljava/lang/Object;
 *
 */
public class Wrapper<T> {

    private T t;

    public T getT() {
        return t;
    }

    public static void main(String[] args) {
        Type type = Wrapper.getGenericType(new Wrapper<String>());
        System.out.println("type of new Wrapper<String>(): " + type);
        type = Wrapper.getGenericType(new Wrapper<List<Map<String, Object>>>() {});
        System.out.println("type of new Wrapper<String>() {}: " + type);
    }

    public static <T> Type getGenericType(Wrapper<T> wrapper) {
        Type type = wrapper.getClass().getGenericSuperclass();
        if (type == null) {
            return null;
        }

        if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type[] types = parameterizedType.getActualTypeArguments();
            return types[0];
        }

        return null;
    }
}