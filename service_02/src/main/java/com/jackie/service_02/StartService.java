package com.jackie.service_02;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/29.
 */
public class StartService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(StartService.this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(StartService.this, "onBind", Toast.LENGTH_SHORT).show();
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(StartService.this, "onStartCommand", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(StartService.this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

}
