package com.jackiee.activity_01;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/18.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(BaseActivity.this, "当前正在进入的Activity为：" + getClass().getSimpleName(), Toast.LENGTH_SHORT).show();
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
