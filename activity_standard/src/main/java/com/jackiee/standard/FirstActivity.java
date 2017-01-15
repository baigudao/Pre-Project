package com.jackiee.standard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/7.
 */
public class FirstActivity extends Activity {

    private Button btn_standard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toast.makeText(FirstActivity.this, "已经启动当前界面！", Toast.LENGTH_SHORT).show();
        btn_standard = (Button) findViewById(R.id.btn_standard);
        btn_standard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, FirstActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(FirstActivity.this, "已经退出当前界面！", Toast.LENGTH_SHORT).show();
    }
}
