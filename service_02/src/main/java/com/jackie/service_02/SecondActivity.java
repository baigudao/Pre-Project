package com.jackie.service_02;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/29.
 */
public class SecondActivity extends Activity {

    private Button button;
    private Button btn_bind;
    private Button btn_onBind;
    private Intent intent;
    private ServiceConnection serviceConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        button = (Button) findViewById(R.id.btn);
        btn_bind = (Button) findViewById(R.id.btn_bind);
        btn_onBind = (Button) findViewById(R.id.btn_onBind);
        intent = new Intent(SecondActivity.this, BindService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Toast.makeText(SecondActivity.this, "与后台服务连接成功：onServiceConnected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Toast.makeText(SecondActivity.this, "与后台服务连接失败：onServiceDisconnected", Toast.LENGTH_SHORT).show();
            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });

        btn_bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });

        btn_onBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });
    }

}
