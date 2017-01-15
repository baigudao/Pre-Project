package com.jackiee.asynctask_02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by baigu on 2016/5/9.
 */
public class FirstActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(FirstActivity.this,SecondActivity.class));
                FirstActivity.this.finish();
            }
        });
    }
}
