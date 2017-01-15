package com.jackiee.broadcast_01;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/5.
 */
public class FirstActivity extends Activity {

    private BroadcastReceiver broadcastReceiver;
    private MyBroadCast myBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        myBroadCast = new MyBroadCast();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(myBroadCast,intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadCast);
    }

    long startTime = 0;
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - startTime) > 3000) {
            Toast.makeText(FirstActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            startTime = currentTime;
        } else {
            finish();
        }
    }
}

