package com.teng.androidms.java;

/**
 * Created by teng on 17/7/29.
 */

public class SingleInstance {

    private SingleInstance(){
        //设置为private,避免在外部被实例化
    }

    ////////////////////////////////////////////////////////////////
    // 单例
    /** TODO: 17/7/29  懒汉式：只有调用getInstance()才会创建单例
    private static SingleInstance singleInstance = null;
    public static SingleInstance getInstance(){
        if (singleInstance == null){
            singleInstance = new SingleInstance();
        }
        return singleInstance;
    }**/

    //懒汉式加锁
    // TODO 做了两次null检查 DCL(double check lock) ，确保了只有第一次调用单例的时候才会做同步，
    // TODO 这样也是线程安全的，同时避免了每次都同步的性能损耗
    private static volatile SingleInstance singleInstance = null;
    // 如果不加 volatile 这种方式在可能造成失效
    // new SingleInstance() 会做三个操作，
    // 1、为SingleInstance对象分配内存  ---> 实例化
    // 2、成员变量初始化和构造方法   ----> 初始化 singleInstance = null
    // 3、SingleInstance对象引用指向内存空间

    // Q: 在java 1.5之前这三步的顺序并不一定按顺序执行，如果先执行了第三步，这时候该对象不为空，但是成员变量没有初始化，
    // 如果另一个线程在这个时候调用该方法，那么实例化的这个对象是没有初始化的。

    // A: 在java 1.5 的时候加了volatile，这个变量相当于加了synchronized ，
    // 这个变量保证了变量是从主存中读写的，而不是工作内存，而且读必须在写发生之后，
    // 所以对象的状态是所有对象都可见的
/**
    public static SingleInstance getInstance(){
        if (singleInstance == null){
            synchronized (SingleInstance.class){
                if (singleInstance == null){
                    singleInstance = new SingleInstance();
                }
            }
        }

        return singleInstance;
    }
**/
    // 懒汉式改进版
    // TODO 利用了classloader的机制来保证初始化instance时只有一个线程，所以也是线程安全的，同时没有性能损耗
    /**
    private static class LazyLoader{
        private static final SingleInstance Instance = new SingleInstance();
    }
    public static final SingleInstance getInstance(){
        return LazyLoader.Instance;
    }
    **/

    ////////////////////////////////////////////////////////////////
    // TODO: 17/7/29  饿汉式 线程安全的
    /**
    private static final SingleInstance SINGLE_INSTANCE = new SingleInstance();

    public static SingleInstance getInstance(){
        return singleInstance;
    }
    **/



}
