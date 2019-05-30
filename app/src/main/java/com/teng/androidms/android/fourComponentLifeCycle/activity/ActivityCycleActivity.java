package com.teng.androidms.android.fourComponentLifeCycle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager;

import com.teng.androidms.R;

public class ActivityCycleActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO setContentView的流程
        /**
         * contentParent 可能是decorView的子类(content view)或者它本身
         * 1、getWindow().setContentView()   其实即使phoneWindow(在Activity的attach方法中创建并传了Activity进去)的setContentView()
         * 2、如果contentParent(也就是DecorView) 为空 调用installDecor()方法去创建一个DecorView
         * 3、mLayoutInflater.inflate(layoutResID, mContentParent)  mLayoutInflater是在构造函数的通过Activity LayoutInflater.from(context)创建的
         * 3.1、LayoutInflater.inflate 其实是一个XML的解析器在解析布局，如果根标签是<merge/> root==null | attachRoot = false 抛出一个merge必须设置root并且attachRoot=true
          */
         setContentView(R.layout.activity_screen1);



        // TODO startActivity的流程
        /**
         * 统一走startActivityForResult
         * Instrumentation 里面的 execStartActivity()方法
         * ActivityManagerNative.getDefault() 也就是ActivityManagerProxy  startActivity
         * ActivityManagerProxy   mRemote.transact(START_ACTIVITY_TRANSACTION, data, reply, 0); 调用远程服务
         * 然后回到ActivityManagerNative  onTransact()
         * ActivityManagerService  startActivity
         *
          */

        startActivity(new Intent());


    }
}
