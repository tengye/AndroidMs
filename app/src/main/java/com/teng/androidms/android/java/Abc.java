package com.teng.androidms.android.java;
// javac Abc.java
// javap -c Abc  查看字节码文件
public class Abc {
    public static void main(String[] arg) {

        String a = "";

        for (int i = 0; i < 100; i++) {
//            a = a.concat("abc");
//            a = a.concat("abc");
            a += "abc";
        }

    }
}
