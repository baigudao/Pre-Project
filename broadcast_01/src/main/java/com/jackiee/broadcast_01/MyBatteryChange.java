package com.jackiee.broadcast_01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/10.
 */
public class MyBatteryChange extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "贝贝，你到底爱我不？", Toast.LENGTH_SHORT).show();
    }
}
