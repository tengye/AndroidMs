package com.teng.androidms.android.fourComponentLifeCycle.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ServiceA extends Service {
    public static final String TAG = "serviceTest";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "serviceA -----> onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "serviceA -----> onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "serviceA -----> onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "serviceA -----> onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "serviceA -----> onUnbind");
        return super.onUnbind(intent);
    }
}
