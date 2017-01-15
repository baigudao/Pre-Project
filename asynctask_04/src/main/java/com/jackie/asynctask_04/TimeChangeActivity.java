package com.jackie.asynctask_04;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/9/2.
 */
public class TimeChangeActivity extends Activity {

    private ImageView imageView;
    private int[] images = { R.drawable.j30, R.drawable.j40, R.drawable.j50,R.drawable.j20};
    private int index = 0;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                if (index >= 4) {
                    index = 0;
                }
                imageView.setImageResource(images[index++]);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timechange);

        imageView = (ImageView) findViewById(R.id.iv);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = handler.obtainMessage(100);
                handler.sendMessage(message);
            }
        }, 500, 500);
    }
}
