package com.teng.androidms.android.fourComponentLifeCycle.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.teng.androidms.R;

public class ServiceCycleActivity extends AppCompatActivity {

    // 先startService(onCreate 然后 onStartCommand) -- > bindService(onBind) --- > stopService (不执行ondDestroy) -- > unbindService (onUnbind 然后 onDestroy)
    // 先startService(onCreate 然后 onStartCommand) -- > bindService(onBind)  -- > unbindService (onUnbind) --- > stopService (ondDestroy)


    // binderService(onCreate 然后 onBind) --> startService(onStartCommand) --> stopService (不执行ondDestroy) -- > unbindService (onUnbind 然后 onDestroy)
    // binderService(onCreate 然后 onBind) --> startService(onStartCommand) --> unbindService (onUnbind) --- > stopService (ondDestroy)

    // bindService 跟调用者的生命周期相关，调用者结束的话会unBinderService


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_cycle);

        final Intent serviceIntent = new Intent(ServiceCycleActivity.this, ServiceA.class);

        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(serviceIntent);
            }
        });

        findViewById(R.id.stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(serviceIntent);
            }
        });

        findViewById(R.id.bind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(serviceIntent, serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.unbind_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });
    }
}