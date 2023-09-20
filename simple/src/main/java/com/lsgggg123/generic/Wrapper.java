package com.lsgggg123.generic;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
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

    static List<Integer> list = new ArrayList<>();

    private T t;

    public T getT() {
        return t;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        Type type = Wrapper.getGenericType(new Wrapper<String>());
        System.out.println("type of new Wrapper<String>(): " + type);

        Wrapper<List<Map<String, Object>>> wrapperInner = new Wrapper<List<Map<String, Object>>>() {};
        type = Wrapper.getGenericType(wrapperInner);
        System.out.println("type of new Wrapper<String>() {}: " + type);
        System.out.println(wrapperInner.getClass() == Wrapper.class);

        Field field = Wrapper.class.getDeclaredField("list");
        Type[] genericType = getGenericType(field);
        System.out.println("type of 'static List<Integer> list = new ArrayList<>();': " + Arrays.toString(genericType));

        Wrapper<Long> wrapper = new Wrapper<>();
        type = Wrapper.getGenericType(wrapper);
        System.out.println("type of local Wrapper<Long> wrapper = new Wrapper<>(): " + type);
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


    public static Type[] getGenericType(Field field) {
        field.setAccessible(true);
        Type genericType = field.getGenericType();
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return parameterizedType.getActualTypeArguments();
        }

        return null;
    }
}