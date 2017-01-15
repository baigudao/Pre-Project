package com.jackie.asynctask_04;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/1.
 */
public class SecondActivity extends Activity {

    private ProgressBar progressBar;
    private Button button;
    private ImageView imageView;
    private MyAsynctask myAsynctask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        progressBar = (ProgressBar) findViewById(R.id.pro);
        button = (Button) findViewById(R.id.btn_downLoad);
        imageView = (ImageView) findViewById(R.id.iv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] src = {"http://img4.imgtn.bdimg.com/it/u=3098431712,1916878776&fm=23&gp=0.jpg",
                        "http://img3.imgtn.bdimg.com/it/u=1828150227,2026932408&fm=23&gp=0.jpg",
                        "http://g.hiphotos.baidu.com/image/h%3D360/sign=8a3819dcc9bf6c81e8372aee8c3fb1d7/962bd40735fae6cd6d3356d50ab30f2443a70ffa.jpg",
                        "http://image.tianjimedia.com/uploadImages/2015/105/07/FYC4TT6O03MN.jpg",
                        "http://image.tianjimedia.com/uploadImages/2015/154/48/VS46E00E16TR.jpg"};
                myAsynctask = new MyAsynctask();
                myAsynctask.execute(src[3]);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        myAsynctask.cancel(true);
    }

    class MyAsynctask extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setMax(100);
        }

        @Override
        protected Object doInBackground(Object[] params) {

            String src = (String) params[0];
            File dest = new File("/sdcard/meiNvMen2.jpg");

            URL url = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                url = new URL(src);

                InputStream inputStream = url.openStream();
                bis = new BufferedInputStream(inputStream);
                int totalSize = bis.available();
                bos = new BufferedOutputStream(new FileOutputStream(dest));

                byte[] bytes = new byte[1024];

                int size = 0;
                for (int count = 0; (count = bis.read(bytes)) != -1; ) {
                    bos.write(bytes, 0, count);
                    size += count;
                    int progressInt = (size / totalSize) * 100;
                    publishProgress(progressInt);
                }
                bos.flush();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            BitmapFactory.Options options = new BitmapFactory.Options();
            BitmapFactory.decodeFile("/sdcard/meiNvMen2.jpg", options);
            options.inJustDecodeBounds = true;

            //图片的宽高
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;

            //获取屏幕的信息
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            display.getMetrics(displayMetrics);

            //获取屏幕的宽高
            int widthPixels = displayMetrics.widthPixels;
            int heightPixels = displayMetrics.heightPixels;

            //计算采样因子
            int wSample = outWidth / widthPixels;
            int hSample = outHeight / heightPixels;

            int sample = (wSample > hSample) ? wSample : hSample;

            options.inJustDecodeBounds = false;
            options.inSampleSize = sample;
            Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/meiNvMen2.jpg", options);


            return bitmap;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            progressBar.setProgress((int) values[0]);
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            progressBar.setVisibility(View.INVISIBLE);
            Bitmap bitmap = (Bitmap) o;
            imageView.setImageBitmap(bitmap);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onCancelled(Object o) {
            super.onCancelled(o);
        }


    }

}


