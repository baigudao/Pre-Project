package com.jackiee.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

/**
 * Created by baigu on 2016/5/7.
 */
public class SecondActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("对话框风格的Activity");
        tv.setPadding(28, 28, 28, 28);
        tv.setGravity(Gravity.CENTER);
        setContentView(tv);
    }
}