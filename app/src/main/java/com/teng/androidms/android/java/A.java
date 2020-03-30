package com.teng.androidms.android.java;

import java.util.Arrays;

public class A {

    public synchronized void print(String name) {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(20);
                System.out.println("hello " + name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("结果：" + func1(new int[] { -1, 5, 1, 6, 2 }));
        System.out.println("结果：" + func1(new int[] { 3, 2, 1, 5, 4 }));
    }

    /* out:
     * [-1, 5, 1, 6, 2] >>
     * [1, 2, 0, 0, 5]
     * 结果：3
     * [3, 2, 1, 5, 4] >>
     * [1, 2, 3, 4, 5]
     * 结果：6
     */

    public static int func1(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int e : arr) {
            if (e < 1 || e > arr.length) {
                continue;
            }
            newArr[e - 1] = e;
        }
        System.out.println(Arrays.toString(arr) + " >> ");
        System.out.println(Arrays.toString(newArr));

        for (int i = 0; i < newArr.length; i++) {
            if (newArr[i] == 0) {
                return i + 1;
            }
        }
        return arr.length + 1;
    }

}
