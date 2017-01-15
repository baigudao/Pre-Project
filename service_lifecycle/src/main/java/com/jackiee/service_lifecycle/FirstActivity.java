package com.jackiee.service_lifecycle;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/7.
 */
public class FirstActivity extends Activity {

    private Button btn;
    private Button start_service;
    private Button stop_service;
    private Button bind_service;
    private Button unbind_service;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btn = (Button) findViewById(R.id.btn);
        start_service = (Button) findViewById(R.id.start_service);
        stop_service = (Button) findViewById(R.id.stop_service);
        bind_service = (Button) findViewById(R.id.bind_service);
        unbind_service = (Button) findViewById(R.id.unbind_service);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });

        start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(FirstActivity.this, MyService.class));
            }
        });
        stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(FirstActivity.this, MyService.class));
            }
        });

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Toast.makeText(FirstActivity.this, "与后台服务通信成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bind_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(FirstActivity.this, MyService.class), serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });
        unbind_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });
    }
}
