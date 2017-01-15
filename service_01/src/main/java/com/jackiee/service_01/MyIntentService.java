package com.jackiee.service_01;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/6.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(MyIntentService.this, "oncreate", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MyIntentService.this, "ondestroy", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        Toast.makeText(MyIntentService.this, "onhandleintent", Toast.LENGTH_SHORT).show();
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(MyIntentService.this, "onunbind", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }
}
