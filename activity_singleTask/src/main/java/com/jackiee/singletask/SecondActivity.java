package com.jackiee.singletask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/7.
 */
public class SecondActivity extends Activity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toast.makeText(SecondActivity.this, "第二个界面已经启动！", Toast.LENGTH_SHORT).show();
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this,FirstActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(SecondActivity.this, "已经结束第二个界面！", Toast.LENGTH_SHORT).show();
    }
}
