package com.jackiee.data_storage01;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by baigu on 2016/5/9.
 */
public class FirstActivity extends Activity {

    private Button btn_sp;
    private Button btn_file;
    private Button btn_sd;
    private TextView textView;
    private SharedPreferences sharedPreferences;
    private int count ;//对这个数据反复的修改

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_sp = (Button) findViewById(R.id.btn_SharedPreferences);
        btn_file = (Button) findViewById(R.id.btn_file);
        btn_sd = (Button) findViewById(R.id.btn_sd);
        textView = (TextView) findViewById(R.id.text);
        sharedPreferences = getSharedPreferences("sp", MODE_PRIVATE);
        count = sharedPreferences.getInt("sp", 1);
        textView.setText("第" + count + "次使用此程序!");
        btn_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SPActivity.class));
            }
        });
        btn_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this,FileActivity.class));
            }
        });
        btn_sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sp", ++count);
        editor.commit();
    }
}
