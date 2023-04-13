/*
 * Ant Group
 * Copyright (c) 2004-2022 All Rights Reserved.
 */

package com.lsgggg123;


import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] b = {-1, -2, -3, -4, -5, -6, -7, -8, -9, 10};
        System.arraycopy(a, 0, b, 0, 10);
        System.out.println(Arrays.toString(b));
    }
}