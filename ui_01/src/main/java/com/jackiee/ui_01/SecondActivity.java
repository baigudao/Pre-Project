package com.jackiee.ui_01;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by baigu on 2016/5/9.
 */
public class SecondActivity extends Activity {

    private ProgressBar progressBar;
    private int progressCount;
    private Button button;
    private TextView textView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 44) {
                progressBar.setProgress(progressCount);
            }

        }
    };
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = (Button) findViewById(R.id.btn);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        textView = (TextView)findViewById(R.id.tv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        doWork();
                    }
                }).start();
            }
        });
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(44);
                if (progressCount >= 100) {
                    timer.cancel();
                }
            }
        }, 0, 10);
    }

    //模拟的耗时操作
    private void doWork() {
        for (progressCount = 1; progressCount <= 100; progressCount++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
