package com.jackie.activity_05.Activity.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.jackie.activity_05.R;

/**
 * Created by Administrator on 2016/11/1.
 */
public class FirstActivity extends Activity {

    private Button button1;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private String [] strings = {"孙悟空","猪八戒","沙和尚","唐僧"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_first);

        button1 = (Button) this.findViewById(R.id.btn1);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this, SecondActivity.class));
            }
        });

        spinner = (Spinner)findViewById(R.id.spinner);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,strings);

        spinner.setAdapter(adapter);

    }
}
