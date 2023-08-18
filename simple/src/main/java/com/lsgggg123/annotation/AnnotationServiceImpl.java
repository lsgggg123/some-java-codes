package com.lsgggg123.annotation;

import java.lang.reflect.Method;

public class AnnotationServiceImpl implements AnnotationService {

    @Override
    public void prepare() {
        System.out.println("prepare");
    }

    public static void main(String[] args) throws Exception {
        AnnotationService service = new AnnotationServiceImpl();
        Method method = service.getClass().getDeclaredMethod("prepare");
        boolean present = method.isAnnotationPresent(Inherit.class);
        System.out.println(present); // 子类无法拿到父类的注解
    }
}