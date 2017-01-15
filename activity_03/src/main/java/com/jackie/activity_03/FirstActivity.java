package com.jackie.activity_03;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/8/23.
 */
public class FirstActivity extends Activity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private TextView textView;
    private static final int REQUEST_CODE = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        button1 = (Button) findViewById(R.id.btn_1);
        button3 = (Button) findViewById(R.id.btn_3);
        button4 = (Button) findViewById(R.id.btn_4);
        button5 = (Button) findViewById(R.id.btn_5);
        button6 = (Button) findViewById(R.id.btn_6);
        button7 = (Button) findViewById(R.id.btn_7);
        button8 = (Button) findViewById(R.id.btn_8);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(FirstActivity.this, SecondActivity.class), REQUEST_CODE);
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ifeng.com"));
                startActivity(intent);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:10086"));
                intent.setType("vnd.android-dir/mms-sms");
                intent.putExtra("sms_body", "这是信息的主体内容！");
                startActivity(intent);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra("output", Uri.parse("sdcard0/tanghao.jpg"));
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("outputX", 320);
                intent.putExtra("outputY", 320);
                intent.putExtra("noFaceDetection", "true");
                intent.putExtra("scale", "true");
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            button2 = (Button) findViewById(R.id.btn_2);
            textView = (TextView) findViewById(R.id.tv_1);
            final String string = data.getStringExtra("key");
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    textView.setText(string);
                }
            });
        }
    }
}
