package com.jackiee.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/7.
 */
public class MainActivity extends Activity {

    private Button startActivity, addFragment, backFragment, replaceFragment, finish ,startActivity1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toast.makeText(MainActivity.this, "Activity中的onCreate方法", Toast.LENGTH_SHORT).show();

        startActivity = (Button) findViewById(R.id.startActivity);
        startActivity1 = (Button)findViewById(R.id.startActivity1);
        addFragment = (Button) findViewById(R.id.addFragment);
        backFragment = (Button) findViewById(R.id.backFragment);
        replaceFragment = (Button) findViewById(R.id.replaceFragment);
        finish = (Button) findViewById(R.id.finish);

        // 为startActivity按钮绑定事件监听器
        startActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        startActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,MainActivity2.class));
            }
        });

        // 为addFragment按钮绑定事件监听器
        addFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                LifecycleFragment fragment = new LifecycleFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });

        // 为backFragment按钮绑定事件监听器
        backFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                SecondFragment fragment = new SecondFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment)
                        .addToBackStack("aa")// 将替换前的Fragment添加到Back栈
                        .commit();
            }
        });

        // 为replaceFragment按钮绑定事件监听器
        replaceFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondFragment fragment = new SecondFragment();
                getFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
            }
        });

        // 为finish按钮绑定事件监听器
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View source) {
                // 结束该Activity
                MainActivity.this.finish();
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(MainActivity.this, "Activity中的onStart方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(MainActivity.this, "Activity中的onResume方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(MainActivity.this, "Activity中的onPause方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(MainActivity.this, "Activity中的onStop方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(MainActivity.this, "Activity中的onDestroy方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(MainActivity.this, "Activity中的onRestart方法", Toast.LENGTH_SHORT).show();
    }
}

