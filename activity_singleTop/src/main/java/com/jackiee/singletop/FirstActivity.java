package com.jackiee.singletop;

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

    private Button btn_others;
    private Button btn_singleTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toast.makeText(FirstActivity.this, "已经启动第一个界面！", Toast.LENGTH_SHORT).show();
        btn_singleTop = (Button) findViewById(R.id.btn_singleTop);
        btn_others = (Button)findViewById(R.id.btn_others);
        btn_others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this,SecondActivity.class));
            }
        });
        btn_singleTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, FirstActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(FirstActivity.this, "已经退出第一个界面！", Toast.LENGTH_SHORT).show();
    }
}