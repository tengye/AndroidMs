package com.teng.androidms.android.sourceCode;

/**
 * Created by teng on 17/7/30.
 */

public class HandlerAndIntentService {

    // TODO Handler
    // 1、MessageQueue 消息队列,在创建Looper的时候同时被创建，在next()方法中循环读取消息
    // 单链表维护，在插入和删除上有优势


    // 2、Looper.prepare(); 创建一个Looper对象, Looper创建的时候会同时创建一个MessageQueue
    // Looper.loop() 也是一个循环，调用MessageQueue的next()方法读取消息，读到的话就处理dispatchMessage()
    // 当Looper的quit()被调用的时候会调用messageQueue的quit(),此时next()会返回null，然后loop()方法也跟着退出。


    // 3、Handler 发送和处理消息


    // Handler通过调用sendMessage方法把消息放在消息队列MessageQueue中，
    // Looper负责把消息从消息队列中取出来，重新再交给Handler进行处理，三者形成一个循环

    // 通过构建一个消息队列，把所有的Message进行统一的管理，当Message不用了，并不作为垃圾回收，而是放入消息队列中，
    // 供下次handler创建消息时候使用，提高了消息对象的复用，减少系统垃圾回收的次数 Message.obtain();

    // 每一个线程，都会单独对应的一个looper，这个looper通过ThreadLocal来创建，
    // 保证每个线程只创建一个looper，looper初始化后就会调用looper.loop创建一个MessageQueue，
    // 这个方法在UI线程初始化的时候就会完成，我们不需要手动创建

    /////////////////////////////////////////////////////////////////////
    // TODO IntentService

    // IntentService继承自Service,由于Service也是运行在主线程的所以并不能进行耗时操作，
    // 但是在Activity中创建的线程并不好管理，例如一个Activity已经销毁但是线程仍在运行，
    // 多个Activity也不能对一个线程进行操作，IntentService是在service的onCreate中创建了一个线程，
    // 然后将Looper对象传给了ServiceHandler(继承自Handler) ,在ServiceHandler的handlerMessage回调方法中执行
    // onHandleIntent 在这个方法中执行耗时操作,线程执行完会调用 stopSelf()结束这个service




}
