package com.teng.androidms.android.fourComponentLifeCycle.fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.teng.androidms.R;

public class FragmentContainer extends AppCompatActivity {

    public static final String TAG = "Fragment";

    // 四种提交事务的方式 commit commitNow  commitAllowingStateLoss  commitNowAllowingStateLoss
    // commit 是异步操作，会将任务提交给主线程，
    // commitNow 会立即执行事务
    // commitAllowingStateLoss 和 commit的区别就是：commit如果在onSaveInstanceState之后提交会抛出IllegalStateException(无效状态异常)
    // 状态的丢掉，指的是可能丢掉FragmentManager状态, 即onSaveInstanceState之后任何被添加或被移除的Fragments.

    // addToBackStack 添加到回退栈中，可以通过返回键来返回到栈中的fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_container);

        Log.d(TAG, "Activity---------onCreate");
        final FragmentA fragmentA = new FragmentA();
        final FragmentB fragmentB = new FragmentB();
        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO A会执行自己的生命周期到onResume,其他的fragment或者activity不受影响
                // TODO add进去的时候 addToBackStack 加入到回退栈的时候并不会调用onDetach,除非它是栈里最后一个元素
                getSupportFragmentManager().beginTransaction().add(R.id.container, fragmentA, "container").addToBackStack("cc").commit();
            }
        });

        findViewById(R.id.replace).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO 如果A已经存在，先走B的onAttach，然后A开始走onPause，-- 到 onDetach, 然后B开始onCreateView -- 到 onResume
                // TODO replace进去的时候 addToBackStack 加入到回退栈的时候会调用onDetach
                getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentB, "container").addToBackStack("cc").commit();
            }
        });

        findViewById(R.id.remove).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().remove(fragmentA).commitAllowingStateLoss();
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Activity---------onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Activity---------onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "Activity---------onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "Activity---------onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "Activity---------onStop");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "Activity---------onDestroy");
    }
}

