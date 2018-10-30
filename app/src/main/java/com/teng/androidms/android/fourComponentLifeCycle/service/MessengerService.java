package com.teng.androidms.android.fourComponentLifeCycle.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;


public class MessengerService extends Service {

    Messenger mMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //TODO 监听客户端发来的消息

        }
    });

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }
}
