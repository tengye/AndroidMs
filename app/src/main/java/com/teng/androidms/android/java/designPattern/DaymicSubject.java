package com.teng.androidms.android.java.designPattern;

import android.view.View;

import com.teng.androidms.android.java.bean.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DaymicSubject implements InvocationHandler {

    private Object targetObject;

    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        ClassLoader classLoader = targetObject.getClass().getClassLoader();
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();
        // 第一个参数：代理类和委托类需要使用同一个类加载器
        // 第二个参数：代理类和委托类需要实现相同的接口
        // 都三个参数：被拦截的方法需要执行哪个InvocationHandler的invoke方法
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 被代理的对象
        // method: 调用的方法
        // args: 调用方法的参数列表
        return method.invoke(targetObject, args);
    }

    public static void main(String[] args) {
        DaymicSubject daymicSubject = new DaymicSubject();
        View.OnClickListener o = (View.OnClickListener) daymicSubject.newProxyInstance(new RealSubject());
//        o.onClick();
    }


}
