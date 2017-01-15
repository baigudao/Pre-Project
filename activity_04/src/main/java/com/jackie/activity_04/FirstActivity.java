package com.jackie.activity_04;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/26.
 */
public class FirstActivity extends Activity {

    private Button button;
    private ListView listView;
    private NewsAdapter newsAdapter;
    private List<News> listData;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        button = (Button) findViewById(R.id.btn_1);
        button2 = (Button)findViewById(R.id.btn_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this,SecondActivity.class));
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(FirstActivity.this).setIcon(R.drawable.akd)
                        .setTitle("退出程序").setMessage("你确定退出吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                FirstActivity.this.finish();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        }).create();
                dialog.show();
            }
        });
        listView = (ListView) findViewById(R.id.lv_1);

        listData = new ArrayList<News>();
        for (int i = 0; i < 10; i++) {
            News news = new News(i + 1, "第" + (i + 1) + "个标题", new Date(), "第" + (i + 1) +
                    "条内容，很长，很长，很长，很长，很长，很长，很长，" +
                    "很长，很长，很长，很长，很长，很长，很长，很长，很长，很长，很长，");
            listData.add(news);
        }

        newsAdapter = new NewsAdapter(this, listData);
        listView.setAdapter(newsAdapter);
    }
}

class NewsAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<News> listData;

    public NewsAdapter(Context context, List<News> listData) {
        layoutInflater = LayoutInflater.from(context);
        this.listData = listData;
    }

    @Override
    public int getCount() {
        return listData == null ? 0 : listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh = null;
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.listview_item,null);

            vh = new ViewHolder();
            vh.title = (TextView) convertView.findViewById(R.id.tv_title);
            vh.time = (TextView) convertView.findViewById(R.id.tv_pubTime);
            vh.content = (TextView) convertView.findViewById(R.id.tv_content);

            convertView.setTag(vh);
        }else {
            vh = (ViewHolder)convertView.getTag();
        }
        News news = listData.get(position);
        vh.title.setText(news.getTitle());
        vh.time.setText(new SimpleDateFormat("yyyy年MM月 HH:mm").format(news.getTime()));
        vh.content.setText(news.getContent());

        return convertView;
    }

    private class ViewHolder {
        TextView title;
        TextView time;
        TextView content;
    }
}
