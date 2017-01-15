package com.jackie.service_02;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/29.
 */
public class BindService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(BindService.this, "onBind", Toast.LENGTH_SHORT).show();
        return new Binder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(BindService.this, "onCreate", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(BindService.this, "onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(BindService.this, "onUnbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

}
