package com.teng.androidms.android.fourComponentLifeCycle.service;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Rect;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewParent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.teng.androidms.IMyAidlInterface;
import com.teng.androidms.R;
import com.teng.androidms.android.aidl.MyService;
//import com.teng.androidms.android.view.MarqueeTextView;

public class ServiceCycleActivity extends Activity {

    // 先startService(onCreate 然后 onStartCommand) -- > bindService(onBind) --- > stopService (不执行ondDestroy) -- > unbindService (onUnbind 然后 onDestroy)
    // 先startService(onCreate 然后 onStartCommand) -- > bindService(onBind)  -- > unbindService (onUnbind) --- > stopService (ondDestroy)


    // binderService(onCreate 然后 onBind) --> startService(onStartCommand) --> stopService (不执行ondDestroy) -- > unbindService (onUnbind 然后 onDestroy)
    // binderService(onCreate 然后 onBind) --> startService(onStartCommand) --> unbindService (onUnbind) --- > stopService (ondDestroy)

    // bindService 跟调用者的生命周期相关，调用者结束的话会unBinderService

    private IMyAidlInterface iMyAidlInterface;

    private Messenger serverMessenger;
    private Messenger mClientMessenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_cycle);

        final Intent serviceIntent = new Intent(ServiceCycleActivity.this, MyService.class);

        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                try {
                    Toast.makeText(getApplicationContext(), iMyAidlInterface.getName(), Toast.LENGTH_LONG).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
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

//        MarqueeTextView marquee = (MarqueeTextView) findViewById(R.id.guide);
//        MarqueeTextView marquee1 = (MarqueeTextView) findViewById(R.id.guide1);
//
//        int screenWidth = getResources().getDisplayMetrics().widthPixels;
//        marquee.setText("我的家bjbcaljhdlas jhdlkjha skljhdlkajs hdljasbdjna sbjhasgdjlgabsld asjn dj王腾");
//        marquee.setSpeed(500f);
//        marquee.init(screenWidth);
//        marquee.startScroll();
//
//        marquee1.setText("我的家bjbcaljhdlas jhdlkjha skljhdlkajs hdljasbdjna sbjhasgdjlgabsld asjn dj王腾");
//        marquee1.setSpeed(1f);
//        marquee1.init(screenWidth);
//        marquee1.startScroll();


        /************************** Messenger *********************/
        mClientMessenger = new Messenger(new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // TODO 接收客户端发来的消息

            }
        });

        ServiceConnection mMessengerConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                serverMessenger = new Messenger(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                serverMessenger = null;
            }
        };

        // TODO RemoteViews
        sendNotification();
    }

    private void sendMessageToServer() {
        // TODO 发送一个消息给 Server
        String msgContent = "我是客户端消息";

        Message message = Message.obtain();
        message.obj = msgContent;
        message.replyTo = mClientMessenger;

        try {
            serverMessenger.send(message);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    private void sendNotification() {
        // TODO 另一个进程显示当前进程的UI
        RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.layout_remote);
        contentView.setTextViewText(R.id.remote_title, "Remote View Title");
        contentView.setTextViewText(R.id.remote_content, "This Remote View Content ... \nThis Remote View Content ... \nThis Remote View Content ...");
        Intent intent = new Intent(this, ServiceCycleActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // RemoteViews的事件只能是PendingIntent
        contentView.setOnClickPendingIntent(R.id.remote_content, pendingIntent);
        Notification notification = new Notification.Builder(this)
                .setWhen(System.currentTimeMillis())    // 设置显示通知的时间
                .setAutoCancel(true)                    // 设置是否可以手动取消
                .setSmallIcon(R.mipmap.ic_launcher)     // 设置在状态栏的小图标，如果没有设置，不显示通知
                .setCustomBigContentView(contentView)   // 设置自定义View，setCustomBigContentView可以显示remoteviews的完整高度，setCustomContentView只能显示系统通知栏高度。
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 发通知
        notificationManager.notify(1, notification);
    }
}
