package com.jackiee.ui_01;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
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
public class FirstActivity extends Activity {

    private Button btn_down;
    private Button button;
    private Button btn_bitmap;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btn_down = (Button) findViewById(R.id.btn_down);
        btn_bitmap = (Button) findViewById(R.id.btn_bitmap);
        imageView = (ImageView) findViewById(R.id.image);
        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this,SecondActivity.class));
            }
        });

        btn_bitmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    File sdCardDir = Environment.getExternalStorageDirectory();
                    File src = new File(sdCardDir,"haiyang.jpg");
                    try {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = false;
                        options.inSampleSize = 10;
                        Bitmap bitmap = BitmapFactory.decodeFile(src.getAbsolutePath(),options);
                        Toast.makeText(FirstActivity.this, "宽度：" + options.outWidth + "\n" + "高度: " + options.outHeight+ "\n"
                                + "图片类型：" + options.outMimeType , Toast.LENGTH_SHORT).show();
                        imageView.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });






        btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = "http://cdn.macd.cn/data/attachment/forum/201504/20/140913ad45aszaaazps0m4.jpg";
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File dest = new File(Environment.getExternalStorageDirectory(), string.substring(string.lastIndexOf('/') + 1));
                    downLoad(string, dest);
                }
                Toast.makeText(FirstActivity.this, "下载完成！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void downLoad(final String src, final File dest) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                BufferedInputStream bufferedInputStream = null;
                BufferedOutputStream bufferedOutputStream = null;

                try {
                    url = new URL(src);
                    bufferedInputStream = new BufferedInputStream(url.openStream());
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(dest));

                    byte[] b = new byte[1024];
                    for (int count = 0; (count = bufferedInputStream.read(b)) != -1; ) {
                        bufferedOutputStream.write(b, 0, count);
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
            }
        }).start();
    }
}
