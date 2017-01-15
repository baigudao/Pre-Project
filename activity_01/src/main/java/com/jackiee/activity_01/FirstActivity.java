package com.jackiee.activity_01;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/7.
 */
public class FirstActivity extends BaseActivity {

    private Button button;
    private ImageView imageView;
    private Button btn_dial;
    private static final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        button = (Button) findViewById(R.id.btn);
        imageView = (ImageView) findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(FirstActivity.this, SecondActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        if (savedInstanceState != null) {
            Toast.makeText(FirstActivity.this, savedInstanceState.getString("abc"), Toast.LENGTH_SHORT).show();
        }
        btn_dial = (Button)findViewById(R.id.btn_dial);
        btn_dial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
        });
        Button btn_call = (Button)findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086")));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            imageView.setImageResource(data.getIntExtra("image", 0));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("abc", "贝贝");
    }

    private long startTime = 0;
    @Override
    public void onBackPressed() {
        long currTime = System.currentTimeMillis();
        if ((currTime - startTime) > 3000) {
            Toast.makeText(FirstActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            startTime = currTime;
        } else {
            FirstActivity.this.finish();
        }
    }
}
