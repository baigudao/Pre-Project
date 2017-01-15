package com.jackiee.asynctask_02;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by baigu on 2016/5/10.
 */
public class ThirdActivity extends Activity {

    private ImageView imageView;
    private int [] images = {R.drawable.aiyinsitan,R.drawable.brucelee,R.drawable.xuyun,R.drawable.zengguofan};
    private int index;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 44){
                if (index >=4){
                    index = 0;
                }
                imageView.setImageResource(images[index++]);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        imageView = (ImageView)findViewById(R.id.imagechange);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(44);
            }
        },5000,5000);
    }
}
