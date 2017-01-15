package com.jackiee.net_02;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by baigu on 2016/5/12.
 */
public class FirstActivity extends Activity {

    private Button btn_httpurlconnection;
    private TextView textView;
    private Button btn_client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_httpurlconnection = (Button) findViewById(R.id.btn_HttpURLConnection);
        btn_client = (Button)findViewById(R.id.btn_HttpClient);
        textView = (TextView) findViewById(R.id.tv);
        btn_httpurlconnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = "http://www.baidu.com";
                new LoadWebTask().execute(src);
            }
        });

        btn_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = "http://www.baidu.com";
                new LoadWebClent().execute(src);
            }
        });
    }

    class LoadWebClent extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            String string = HttpHelper.getWeb(params[0]);
            return string;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }

    class LoadWebTask extends AsyncTask<String, Void, String>{
        StringBuilder stringBuilder = new StringBuilder();
        @Override
        protected String doInBackground(String... params) {
            URL url = null;
            BufferedReader bufferedReader = null;
            HttpURLConnection httpURLConnection = null;
            try {
                url = new URL(params [0]);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                for (String string = null; (string = bufferedReader.readLine()) != null;){
                    stringBuilder.append(string);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }
}
