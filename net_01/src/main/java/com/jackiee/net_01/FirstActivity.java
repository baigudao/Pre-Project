package com.jackiee.net_01;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by baigu on 2016/5/8.
 */
public class FirstActivity extends Activity {

    private Button btn_downLoad;
    private ProgressBar progressBar;
    private ImageView imageView;
    private int progressCount;//进度值
    private String src = "http://p.jiaodong.net/auto/editorfiles/dashengchang/images/jdauto_63_20150114_102951.jpg";
    private String pathName = src.substring(src.lastIndexOf("/") + 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        imageView = (ImageView)findViewById(R.id.image);
        btn_downLoad = (Button) findViewById(R.id.btn_download);
        btn_downLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File sdCardDir = Environment.getExternalStorageDirectory();
                    final File dest = new File(sdCardDir, pathName);

                    new AsyncTask<String, Integer, Bitmap>() {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            progressBar.setMax(100);
                            progressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        protected void onPostExecute(Bitmap bitmap) {
                            super.onPostExecute(bitmap);
                            progressBar.setVisibility(View.INVISIBLE);
                            imageView.setImageBitmap(bitmap);
                        }

                        @Override
                        protected void onProgressUpdate(Integer... values) {
                            super.onProgressUpdate(values);
                            progressBar.setProgress(values[0]);
                        }

                        @Override
                        protected void onCancelled() {
                            super.onCancelled();
                        }

                        @Override
                        protected Bitmap doInBackground(String... params) {
                            URL url = null;
                            HttpURLConnection httpURLConnection = null;
                            BufferedInputStream bufferedInputStream = null;
                            BufferedOutputStream bufferedOutputStream = null;

                            try {
                                url = new URL(params[0]);
                                httpURLConnection = (HttpURLConnection) url.openConnection();
                                httpURLConnection.setRequestMethod("GET");
                                httpURLConnection.setReadTimeout(8000);
                                httpURLConnection.setConnectTimeout(8000);
                                bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest));
                                byte[] bytes = new byte[1024];
                                int size = 0;
                                for (int count = 0; (count = bufferedInputStream.read(bytes)) != -1; ) {
                                    bufferedOutputStream.write(bytes, 0, count);
                                    size += count;
                                    progressCount = (size / bufferedInputStream.available()) * 100;
                                    publishProgress(Integer.valueOf(progressCount));
                                }
                                bufferedOutputStream.flush();

                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                if (bufferedInputStream != null) {
                                    try {
                                        bufferedInputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if (bufferedOutputStream != null) {
                                    try {
                                        bufferedOutputStream.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            Bitmap bitmap = BitmapFactory.decodeFile(dest.getAbsolutePath());
                            return bitmap;
                        }
                    }.execute(src);
                }
            }
        });
    }
}