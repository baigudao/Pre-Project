package com.jackiee.asynctask_02;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Created by baigu on 2016/5/9.
 */
public class SecondActivity extends Activity {

    private Button button;
    private Button btn_down;
    private Button btn_show;
    private String destName;
    private ImageView iv_show;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 44) {
                Toast.makeText(SecondActivity.this, "下载成功！", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        button = (Button) findViewById(R.id.btn);
        btn_down = (Button) findViewById(R.id.btn_down);
        btn_show = (Button) findViewById(R.id.btn_show);
        iv_show = (ImageView) findViewById(R.id.iv_show);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //获取屏幕信息
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        //获取屏幕宽高
        int sw = displayMetrics.widthPixels;
        int sh = displayMetrics.heightPixels;
        Toast.makeText(SecondActivity.this, "屏幕宽：" + sw + "\n" + "屏幕高：" + sh, Toast.LENGTH_SHORT).show();

        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File sdCardDir = Environment.getExternalStorageDirectory();
                    File dest = new File(sdCardDir, destName);
                    Bitmap bitmap = BitmapFactory.decodeFile(dest.getAbsolutePath());
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;
                    int imagew = options.outWidth;
                    int imageh = options.outHeight;
                    //获取屏幕信息
                    WindowManager windowManager = getWindowManager();
                    Display display = windowManager.getDefaultDisplay();
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    display.getMetrics(displayMetrics);
                    //获取屏幕宽高
                    int sw = displayMetrics.widthPixels;
                    int sh = displayMetrics.heightPixels;
                    int samplew = imagew / sw;
                    int sampleh = imageh / sh;

                    options.inJustDecodeBounds = false;
                    options.inSampleSize = (sampleh > samplew) ? sampleh : samplew;
                    Toast.makeText(SecondActivity.this, "kuan:" +bitmap.getWidth() + "gao:" + bitmap.getHeight(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(SecondActivity.this, "保存路径名为：" + dest.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    iv_show.setImageBitmap(bitmap);

                }
            }
        });

        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = "http://img3.3lian.com/2013/c2/64/d/73.jpg";
                destName = src.substring(src.lastIndexOf("/") + 1);
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File sdCardDir = Environment.getExternalStorageDirectory();
                    File dest = new File(sdCardDir, destName);
                    downLoad(src, dest);
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });
    }

    long startTime = 0;
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - startTime) > 3000) {
            Toast.makeText(SecondActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            startTime = currentTime;
        } else {
            SecondActivity.this.finish();
        }
    }

    private void downLoad(final String src, final File dest) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                BufferedInputStream bis = null;
                BufferedOutputStream bos = null;
                try {
                    url = new URL(src);
                    bis = new BufferedInputStream(url.openStream());
                    bos = new BufferedOutputStream(new FileOutputStream(dest));
                    byte b[] = new byte[1024];
                    for (int count = 0; (count = bis.read(b)) != -1; ) {
                        bos.write(b, 0, count);
                    }
                    bos.flush();
                    handler.sendEmptyMessage(44);
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
            }
        }).start();
    }
}
