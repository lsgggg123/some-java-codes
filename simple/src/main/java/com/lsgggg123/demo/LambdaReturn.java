package com.lsgggg123.demo;

import java.util.stream.Stream;

public abstract class LambdaReturn {
    public static void main(String[] args) {
        Stream.of("1", "2", "3", "4", "5").forEach(s -> {
            if ("1".equals(s)) {
                return;
            }
            System.out.println(s);
        });
    }
}