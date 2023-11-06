package com.lsgggg123.sort;

import java.util.Arrays;

public class SimplestSort {

    static int[] arr1 = {2, 8, 4, 1, 3, 0, 9, 7, 5, 6};
    static int[] arr2 = {2, 8, 4, 1, 3, 0, 9, 7, 5, 6};

    public static void main(String[] args) {
        sort1(arr1);
        System.out.println(Arrays.toString(arr1));

        sort2(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    static void sort1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    static void sort2(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    static void swap(int[] arr, int m, int n) {
        int tmp = arr[m];
        arr[m] = arr[n];
        arr[n] = tmp;
    }
}
