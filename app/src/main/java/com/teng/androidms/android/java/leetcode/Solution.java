package com.teng.androidms.android.java.leetcode;

import com.teng.androidms.android.java.A;

import java.util.ArrayList;
import java.util.Arrays;

class Solution {

    public static void main(String[] args) {
        int solution = solution(80);
//        String s = toBinaryString(10);
        System.out.println(solution);
//        System.out.println(s);
//        int i = solution2(new int[]{2, 8, 4, 3, 2}, 1, 1, 1);
//        System.out.println(i);
    }




    public static int solution(int n) {
        int[] d = new int[30];
        int l = 0;
        int p;
        while (n > 0) {
            d[l] = n % 2;
            n /= 2;
            l++;
        }
        for (p = 1; p < 1 + l; ++p) {
            int i;
            boolean ok = true;
            for (i = 0; i < l - p; i++) {
                if (d[i] != d[i + p] || !(p <= l/2)) {
                    ok = false;
                    break;
                }
            }
            if (ok && p != l ) {
                System.out.println(p);
                return p;
            }
        }
        return -1;
    }

//    public static int solution(int n) {
////        int[] d = new int[30];
//        ArrayList<Integer> integers = new ArrayList<>();
//        int l = 0;
//        int p;
//        while (n > 0) {
//            integers.add(0, n % 2);
////            d[l] = n % 2;
//            n /= 2;
//            l++;
//        }
//
////        for (int i = 0; i < d.length; i++) {
////            System.out.print(d[i]);
////        }
////        System.out.println("");
////
////        int[] d = new int[7];
//
//        System.out.println("l == " + l);
//
//        for (p = 1; p < 1 + l; ++p) {
//            int i;
//            boolean ok = true;
//            for (i = 0; i < l - p; ++i) {
//                System.out.println(integers.get(i) + " == " + integers.get(i+p));
//                System.out.println(!integers.get(i).equals(integers.get(i+p)));
//                if (!integers.get(i).equals(integers.get(i+p))) {
//                    ok = false;
//                    break;
//                }
//            }
//            if (ok) {
//                return p;
//            }
//        }
//        return -1;
//    }




//    public static int solution2(int[] A, int X, int Y, int Z) {
//        // write your code in Java SE 8
//        int lastTimeX = 0;
//        int lastTimeY = 0;
//        int lastTimeZ = 0;
//        int[] makeTimeArray = new int[A.length];
//        for (int i = 0; i < A.length; i++) {
//            if (A[i] <= X) {
//                X -= A[i];
//                makeTimeArray[i] = lastTimeX;
//                lastTimeX += A[i];
//            } else if (A[i] <= Y) {
//                Y -= A[i];
//                makeTimeArray[i] = lastTimeY;
//                lastTimeY += A[i];
//            } else if (A[i] <= Z) {
//                Z -= A[i];
//                makeTimeArray[i] = lastTimeZ;
//                lastTimeZ += A[i];
//            } else {
//                return -1;
//            }
//        }
//        int maxTime = 0;
//        for (int i = 0; i < makeTimeArray.length; i++) {
//            maxTime = Math.max(maxTime, makeTimeArray[i]);
//        }
//        return maxTime;
//    }
//
//
//    public static int solution1(int[] X, int[] Y) {
//        // write your code in Java SE 8
//        for (int i = 1; i < X.length; i++) {
//            int j;
//            int temp = X[i];
//            for (j = i; j > 0 && X[j-1] > temp; j--) {
//                X[j] = X[j-1];
//            }
//            X[j] = temp;
//        }
//        int k = 0;
//        for (int i = 1; i < X.length; i++) {
//            k = Math.max(X[i] - X[i-1], k);
//        }
//
//        return k;
//    }
//
//    public static String toBinaryString(int n) {
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(n % 2);
//        n /= 2;
//        while (n != 0) {
//            stringBuilder.insert(0, n % 2);
//            n = n / 2;
//        }
//        return stringBuilder.toString();
//    }
}
