package com.jackiee.ui_02;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by baigu on 2016/5/14.
 */
public class FirstActivity extends Activity {

    private Button btn_send;
    private Button btn_cancel;
    private NotificationManager notificationManager;
    private Notification.Builder builder;
    private Notification notification;
    private Button btn_show;
    private Button btn_hide;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_send = (Button) findViewById(R.id.sendNotification);
        btn_cancel = (Button) findViewById(R.id.cancelNotification);
        btn_show = (Button)findViewById(R.id.btn_show);
        btn_hide = (Button)findViewById(R.id.btn_hide);
        actionBar = getActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        //actionBar.setDisplayShowHomeEnabled(false);
        //actionBar.setHomeButtonEnabled(true);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(FirstActivity.this);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 44, new Intent(FirstActivity.this, NotificationActivity.class), 0);

        notification = builder.setAutoCancel(true).setTicker("提示消息")
                .setSmallIcon(R.drawable.home)
                .setContentTitle("通知内容的标题")
                .setContentText("这是通知的内容，很长。。。。。。。。。。。。。。。。。")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_house))//通知内容的图标
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .build();
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.notify(11, notification);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationManager.cancel(11);
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.show();
            }
        });
        btn_hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionBar.hide();
            }
        });
    }
}
