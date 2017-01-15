package com.jackiee.data_storage01;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by baigu on 2016/5/9.
 */
public class FileActivity extends Activity {

    private EditText editText;
    private Button button_save;
    private TextView textView;
    private Button button_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        editText = (EditText) findViewById(R.id.edit_rizhi);
        button_save = (Button) findViewById(R.id.btn_sd);
        textView = (TextView) findViewById(R.id.tv);
        button_show = (Button) findViewById(R.id.btn_skip);
        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write();
            }
        });
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                read();
            }
        });
    }

    private void write() {
        String string_write = editText.getText().toString();
        FileOutputStream fileOutputStream = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileOutputStream = openFileOutput("rizhi.txt", MODE_APPEND);
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            bufferedWriter.write(string_write);
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void read() {
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStream = openFileInput("rizhi.txt");
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            for (String str = null;(str = bufferedReader.readLine()) != null;){
                stringBuilder.append(str + "\n");
            }
            textView.setText(stringBuilder.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
