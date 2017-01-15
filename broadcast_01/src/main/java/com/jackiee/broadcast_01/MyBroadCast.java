package com.jackiee.broadcast_01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/9.
 */
public class MyBroadCast extends BroadcastReceiver {

    private NetworkInfo networkInfo;
    private ConnectivityManager connectivityManager ;

    @Override
    public void onReceive(Context context, Intent intent) {

        connectivityManager =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isAvailable()){
            Toast.makeText(context, "网络可用", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "网络不可用", Toast.LENGTH_SHORT).show();
        }
    }
}
