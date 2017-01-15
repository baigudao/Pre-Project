package com.jackiee.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/7.
 */
public class LifecycleFragment extends Fragment {


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Toast.makeText(this.getActivity(), "Fragment中的onAttach方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this.getActivity(), "Fragment中的onCreate方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle data) {
        Toast.makeText(this.getActivity(), "Fragment中的onCreateView方法", Toast.LENGTH_SHORT).show();
        TextView tv = new TextView(getActivity());
        tv.setGravity(Gravity.CENTER_HORIZONTAL);
        tv.setText("测试的Fragment");
        tv.setTextColor(getResources().getColor(R.color.colorAccent));
        tv.setTextSize(30);
        return tv;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(this.getActivity(), "Fragment中的onActivityCreated方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(this.getActivity(), "Fragment中的onStart方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(this.getActivity(), "Fragment中的onResume方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(this.getActivity(), "Fragment中的onPause方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(this.getActivity(), "Fragment中的onStop方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(this.getActivity(), "Fragment中的onDestroyView方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this.getActivity(), "Fragment中的onDestroy方法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(this.getActivity(), "Fragment中的onDetach方法", Toast.LENGTH_SHORT).show();
    }
}
