package com.jackie.net_06;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/11/23.
 */
public class MainActivity extends Activity {

    private Button button1;
    private WebView webView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 323) {
                StringBuilder string = (StringBuilder) msg.obj;
                //webView.setText(string);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.btn_1);
        webView = (WebView) findViewById(R.id.tv);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                webView.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl("http://192.168.1.100:8080/servlet-01-core/login.html");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringUrl = "http://192.168.1.100:8080/servlet-01-core/login.html";
                useHttpURLConnection(stringUrl);
            }
        });

    }

    private void useHttpURLConnection(final String stringUrl) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection httpURLConnection = null;
                URL url = null;
                try {
                    url = new URL(stringUrl);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(8000);
                    httpURLConnection.setReadTimeout(8000);
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }

                    Message message = new Message();
                    message.what = 323;
                    message.obj = stringBuilder;
                    handler.sendMessage(message);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }

            }
        }).start();

    }
}
