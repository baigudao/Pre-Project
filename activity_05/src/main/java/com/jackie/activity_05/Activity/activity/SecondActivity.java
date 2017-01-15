package com.jackie.activity_05.Activity.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jackie.activity_05.Activity.entity.News;
import com.jackie.activity_05.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2016/11/1.
 */
public class SecondActivity extends Activity {

    private ListView listView;
    private NewsAdapter newsAdapter;
    private List<News> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = (ListView) findViewById(R.id.lv);

        datas = new ArrayList<>();
        datas.add(new News(0, "赵毅", "内容很长", new Date()));
        datas.add(new News(1, "赵毅2", "内容很长", new Date()));

        newsAdapter = new NewsAdapter(this, datas);
        listView.setAdapter(newsAdapter);
    }
}

class NewsAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<News> datas;

    public NewsAdapter(Context context, List<News> datas) {
        layoutInflater = LayoutInflater.from(context);
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return datas.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.activity_item_news, null);
            viewHolder = new ViewHolder();

            viewHolder.titel = (TextView) convertView.findViewById(R.id.news_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.news_content);
            viewHolder.time = (TextView) convertView.findViewById(R.id.news_time);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.titel.setText(datas.get(position).getTitle());
        viewHolder.content.setText(datas.get(position).getContent());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日 HH:mm", Locale.CHINA);
        viewHolder.time.setText(simpleDateFormat.format(datas.get(position).getTime()));
        return convertView;
    }
}

class ViewHolder {
    TextView titel;
    TextView content;
    TextView time;
}