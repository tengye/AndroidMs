package com.teng.androidms.android.network.okhttp;

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

    public void getData(){
        OkHttpClient client = new OkHttpClient.Builder().build();
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
