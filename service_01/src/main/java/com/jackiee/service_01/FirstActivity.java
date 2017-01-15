package com.jackiee.service_01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends Activity {


    private Button btn;
    private Button start_service;
    private Button stop_service;
    private Button bind_service;
    private Button unbind_service;
    private ServiceConnection serviceConnection;
    private MyService.MyBinder myBinder;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn = (Button) findViewById(R.id.btn);
        start_service = (Button) findViewById(R.id.start_service);
        stop_service = (Button) findViewById(R.id.stop_service);
        bind_service = (Button) findViewById(R.id.bind_service);
        unbind_service = (Button) findViewById(R.id.unbind_service);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(5000);
                }
            }
        });
        /*
        stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(FirstActivity.this, MyService.class));
            }
        });
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bind_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService(new Intent(FirstActivity.this,MyService.class),serviceConnection, Service.BIND_AUTO_CREATE);
            }
        });
        unbind_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(serviceConnection);
            }
        });*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /*
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                myBinder = (MyService.MyBinder)service;
                myBinder.test(FirstActivity.this);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        bindService(new Intent(FirstActivity.this, MyService.class), serviceConnection, Service.BIND_AUTO_CREATE);*/
        startService(new Intent(FirstActivity.this, MyIntentService.class));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unbindService(serviceConnection);
        vibrator.cancel();
    }
}
