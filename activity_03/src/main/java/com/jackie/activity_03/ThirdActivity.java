package com.jackie.activity_03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2016/8/24.
 */
public class ThirdActivity extends Activity {

    private Button button;
    private ListView listView;
    private ArrayAdapter adapter;
    private List<String> stringData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_third);

        button = (Button) findViewById(R.id.btn_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringData.add(UUID.randomUUID().toString());
                adapter.notifyDataSetChanged();
            }
        });

        listView = (ListView) findViewById(R.id.lv_1);

        stringData = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stringData.add("第" + (i + 1) + "条数据内容，请注意查收！");
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stringData);

        listView.setAdapter(adapter);
    }
}
