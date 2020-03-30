package com.teng.androidms.android.java.leetcode;

public class FindNThDigit {

    public static void main(String[] args) {
        FindNThDigit findNThDigit = new FindNThDigit();
        int nthDigit = findNThDigit.findNthDigit(192);
        System.out.println("findNThDigit ----> " + nthDigit);
    }

    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int start = 1;

        while (n > count * len) {
            n -= count * len;
            len+=1;
            count *= 10;
            start *= 10;
        }

        start += (n-1) / len;
        String realStr = Integer.toString(start);
        return Character.getNumericValue(realStr.charAt((n-1) % len));
    }


//    public int findNthDigit(int n) {
//        int len = 1;//用于记录所在数字的位数
//        long count = 9;
//        int start = 1;
//
//        while (n > len * count) {
//            n -= len * count;
//            len += 1;
//            count *= 10;
//            start *= 10;
//        }
//
//        start += (n - 1) / len;//找到所在的那个数
//        String s = Integer.toString(start);
//        return Character.getNumericValue(s.charAt((n - 1) % len));
//    }//确定具体在第几位

//    public int findNthDigit(int n) {
//        int i = 1;
//        int sum = 0;
//
//        if (n < 10) {
//            return n-1;
//        }
//
//        while (true) {
//            sum += (9 * i * Math.pow(10, i - 1));
//            if (n <= sum) {
//                break;
//            }
//            i++;
//        }
//
//        //第二步：确定n是出于位数为len + 1的数字中的第几个数字，并且转换为字符串
//        String resStr = String.valueOf((int)Math.pow(10, i) + (n - 1) / (i + 1));
//        return 0;
        //返回n在这个数字中对应的位，（比如当n == 15，求得len = 1，n = 6， resStr = “12”,然后取出“12”字符串的第二位）
//        return resStr[n - (n - 1) / (i + 1) * (i + 1) - 1] - '0';//注意返回的int数字，并不是返回字符'k',需要减去'0'
//    }
//        int fontNum = n - (9 + 9 * (int) Math.pow(10, i - 3));
//        int index = fontNum / i;
//
//        return (int) Math.pow(10, i - 1) + (index - 1);
//    }
}
