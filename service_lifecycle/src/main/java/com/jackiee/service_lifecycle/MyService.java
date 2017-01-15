package com.jackiee.service_lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/7.
 */
public class MyService extends Service {

    private Binder binder = new Binder(){};

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(MyService.this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this, "onStartCommand", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MyService.this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(MyService.this, "onBind", Toast.LENGTH_SHORT).show();
        return binder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(MyService.this, "onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Toast.makeText(MyService.this, "onRebind", Toast.LENGTH_SHORT).show();
        super.onRebind(intent);
    }
}
