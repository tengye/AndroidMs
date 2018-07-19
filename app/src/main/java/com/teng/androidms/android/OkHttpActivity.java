package com.teng.androidms.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.teng.androidms.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.EventListener;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpActivity extends Activity {

    private Button getAsyncData;
    private EditText result;

    public static final String TAG = "OkHttpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        getAsyncData = (Button) findViewById(R.id.getAsyncData);
        result = (EditText) findViewById(R.id.result);
        getAsyncData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    // TODO 关键的类
    // OKHttpClient  RealCall EventListener(监听请求的过程)
    // RealInterceptorChain Dispatcher StreamAllocation
    // TODO 关键的接口
    // Call Interceptor Interceptor.Chain

    // TODO OKHttpClient
    // OKHttp是一个Call的工厂,一个应用最好是复用一个OkHttpClient,
    // 因为每一个OKHttpClient都维护了一个自己的连接池(ConnectionPool)和线程池(Dispatcher)，复用可以减少等待的时间，减少内存
    // 支持call 和 webSocket模式
    // 通过new Builder来自定义配置

    // TODO RealCall 创建一个真正的请求，实现Call接口
    // 1、newCall -> newRealCall 创建一个Call,并且对Call创建监听
    // 2、execute()同步请求
    // 3、enqueue() 异步请求，创建一个AsyncCall将请求压入OKHttpClient管理的请求队列
    // 4、retryAndFollowUpInterceptor
    // 5、在构造方法中创建eventListener监听整个请求的过程


    // TODO Dispatcher 何时执行异步请求的策略
    // 1、构造方法里创建一个线程池，Q:keepAliveTime的作用， 队列的继承关系，这里用哪种队列好？
    // 2、通过enqueue方法添加请求到请求队列，当正在运行的队列数量小于最大的请求数&&小于每一个主机下可以跑的最大请求数时，添加到运行队列，直接运行，否则添加到准备队列等待
    // 3、Q：等待队列的请求添加到运行队列的时机？

    // TODO StreamAllocation  该类协调三个实体之间的关系：
    // 1、Connections 到远程服务器的物理套接字连接。 这些可能很慢建立，因此必须能够取消当前连接的连接。
    // 2、Streams：在连接上分层的逻辑HTTP请求/响应对。 每个连接都有自己的分配限制，该限制定义了连接可以携带的并发流的数量。 HTTP / 1.x连接一次可以携带1个流，HTTP / 2通常携带多个。
    // 3、Calls：流的逻辑序列，通常是初始请求及其后续请求。 我们希望将单个呼叫的所有流保持在同一连接上，以获得更好的行为和位置。


    public void getData(){
        OkHttpClient client = new OkHttpClient.Builder().eventListenerFactory(new EventListener.Factory() {
            @Override
            public EventListener create(Call call) {
                return null;
            }
        }).build();
        OkHttpClient.Builder builder = client.newBuilder();
        Request request = new Request.Builder()
                .url("http://v.juhe.cn/toutiao/index?type=top&key=5bca71befd51bfc2b56e32a9f687ff3f")
                .build();

        // 这里的回调是子线程...
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "fail");
            }
            @Override
            public void onResponse(Call call, final Response response) {
                Log.d(TAG, response.message());
            }
        });
    }

    public void postData(){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("id", "12344");

        Request request = new Request.Builder()
                .url("http://v.juhe.cn/toutiao/index?type=top&key=5bca71befd51bfc2b56e32a9f687ff3f")
                .post(formBody.build())
                .build();

        // 这里的回调是子线程...
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "fail");
            }
            @Override
            public void onResponse(Call call, final Response response) {
                Log.d(TAG, response.message());
            }
        });
    }

}
