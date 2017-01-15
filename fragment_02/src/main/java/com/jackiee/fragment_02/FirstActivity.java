package com.jackiee.fragment_02;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by baigu on 2016/5/12.
 */
public class FirstActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment1_contain, new FirstFragment());
        fragmentTransaction.add(R.id.fragment2_contain, new SecondFragment());
        //fragmentTransaction.add(R.id.fragment3_contain, new ThirdFragment());
        fragmentTransaction.commit();
    }
}
