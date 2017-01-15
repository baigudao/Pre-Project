package com.jackie.activity_03;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/23.
 */
public class SecondActivity extends Activity {

    private EditText editText;
    private Button button1;
    private Button button2;
    private Button button3;
    private Spinner spinner;
    private ArrayAdapter adapter;
    private String[] strings = {"广州", "深圳", "上海", "北京"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);
        Toast.makeText(SecondActivity.this, "onCreate", Toast.LENGTH_SHORT).show();
        button1 = (Button) findViewById(R.id.btn_1);
        button2 = (Button) findViewById(R.id.btn_2);
        button3 = (Button) findViewById(R.id.btn_3);
        editText = (EditText) findViewById(R.id.et_1);
        spinner = (Spinner) findViewById(R.id.spi_1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("key", editText.getText().toString());
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, AlertDialogActivity.class));
            }
        });

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, strings);

        spinner.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(SecondActivity.this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(SecondActivity.this, "onResume", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(SecondActivity.this, "onPause", Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(SecondActivity.this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(SecondActivity.this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(SecondActivity.this, "onDestroy", Toast.LENGTH_SHORT).show();
    }


}
