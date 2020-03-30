package com.teng.androidms.android.java.designPattern;

/**
 * 优点： 更好的复用性，更好的扩展性
 * 缺点： 系统零乱，关系混乱，调用关系混乱
 */
public class AdapterMode {

    // TODO 类的适配器模式
    // Target (interface 1，2 方法) <----  适配器角色(类 再实现一个2方法)  ---->  源角色(类 1方法)
    // 适配器角色继承了源角色实现了Target接口


    // TODO 对象的适配器模式
    // Target (interface 1，2 方法) <----  适配器角色(包含一个源角色实现1方法，自己定义一个2方法)  ---->  源角色(类 1方法)


    // TODO 缺省的适配器模式
    // 1、定义一个接口
    // 2、写一个抽象类实现这个接口，并实现所有的方法，可能是空实现
    // 3、其他的类来继承这个抽象类，根据需要实现自己想要实现的方法


    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
//        builder.append()

    }

}
