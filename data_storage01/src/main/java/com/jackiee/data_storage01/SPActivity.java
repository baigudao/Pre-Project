package com.jackiee.data_storage01;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/9.
 */
public class SPActivity extends Activity {

    private EditText editText_name;
    private EditText editText_phone;
    private Button button_save;
    private Button button_show;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        editText_name = (EditText)findViewById(R.id.et_name);
        editText_phone = (EditText)findViewById(R.id.et_phone);
        button_save = (Button)findViewById(R.id.btn_save);
        button_show = (Button)findViewById(R.id.btn_show);
        sharedPreferences = this.getSharedPreferences("name_phone", Context.MODE_PRIVATE);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String string = editText_name.getText().toString();
                String string1 = editText_phone.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("name",string);
                editor.putString("phone",string1);
                editor.commit();
            }
        });
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = sharedPreferences.getString("name", null);
                String str_phone = sharedPreferences.getString("phone",null);
                Toast.makeText(SPActivity.this, "你输入的姓名是：" + str_name+ "\n 你输入的电话号码是：" + str_phone, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
