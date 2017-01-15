package com.jackiee.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by baigu on 2016/5/7.
 */
public class SecondFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle data) {
        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText("第二个Fragment");
        tv.setTextSize(30);
        return tv;
    }
}

