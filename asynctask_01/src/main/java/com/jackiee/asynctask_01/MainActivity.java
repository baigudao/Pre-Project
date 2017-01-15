package com.jackiee.asynctask_01;

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
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by baigu on 2016/5/6.
 */
public class MainActivity extends Activity {


    private TextView textView;
    private ProgressBar progressBar;
    private Button button;
    private ImageView imageView;
    private AsyncTask<String, Integer, Bitmap> asyncTask;
    private String src = "http://attach.bbs.miui.com/forum/201512/16/170519mwimihimihqm5ik0.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        button = (Button) findViewById(R.id.btn1);
        imageView = (ImageView) findViewById(R.id.image);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asyncTask = new AsyncTask<String, Integer, Bitmap>() {

                    @Override
                    protected void onPreExecute() {
                        super.onPreExecute();
                        textView.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    protected Bitmap doInBackground(String... params) {
                        String string = params[0];
                        Bitmap bitmap = null;
                        URL url = null;
                        BufferedInputStream bufferedInputStream = null;
                        BufferedOutputStream bufferedOutputStream = null;

                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                            File sdCardDir = Environment.getExternalStorageDirectory();
                            File dest = new File(sdCardDir, "test.jpg");
                            try {
                                url = new URL(string);
                                bufferedInputStream = new BufferedInputStream(url.openStream());
                                int totalSize = bufferedInputStream.available();
                                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest));

                                byte[] b = new byte[1024];

                                int doneSize = 0;
                                for (int count = 0; (count = bufferedInputStream.read(b)) != -1; ) {
                                    bufferedOutputStream.write(b, 0, count);
                                    doneSize += count;
                                    int progressCount = (doneSize / totalSize) * 100;
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
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = false;
                            options.inSampleSize = 10;
                            bitmap = BitmapFactory.decodeFile(dest.getAbsolutePath(), options);
                        }
                        return bitmap;
                    }

                    @Override
                    protected void onProgressUpdate(Integer... values) {
                        super.onProgressUpdate(values);
                        progressBar.setProgress(values[0]);
                    }

                    @Override
                    protected void onPostExecute(Bitmap bitmap) {
                        super.onPostExecute(bitmap);
                        textView.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                        imageView.setImageBitmap(bitmap);
                    }

                    @Override
                    protected void onCancelled() {
                        super.onCancelled();
                        textView.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }.execute(src);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (asyncTask != null) {
            asyncTask.cancel(true);
        }
    }
}
