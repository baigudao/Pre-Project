package com.jackiee.multimedia_01;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

/**
 * Created by baigu on 2016/5/8.
 */
public class FirstActivity extends Activity {

    private Button btn_1;
    private Button btn_2;
    private Button btn_3;
    private Button btn_4;
    private MediaPlayer mediaPlayer;
    private VideoView videoView;
    private Button btn_5;
    private Button btn_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        btn_1 = (Button)findViewById(R.id.btn_yingyoug);
        btn_2 = (Button)findViewById(R.id.btn_yuanshi);
        btn_3 = (Button)findViewById(R.id.btn_sd);
        btn_4 = (Button)findViewById(R.id.btn_net);
        btn_5 = (Button)findViewById(R.id.btn_video);
        btn_6 = (Button)findViewById(R.id.btn_recorder);
        videoView = (VideoView)findViewById(R.id.vv);

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               //mediaPlayer = MediaPlayer.create(FirstActivity.this,R.raw.tiexuenaner);
               // mediaPlayer.setLooping(true);
               // mediaPlayer.start();

            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                try {
                    AssetFileDescriptor assetFileDescriptor = FirstActivity.this.getAssets().openFd("jackiechen.mp3");
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(),assetFileDescriptor.getStartOffset(),assetFileDescriptor.getLength());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource("/sdcard/piaoxue.mp3");
                    mediaPlayer.setLooping(true);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://thsy.bbzx.wuxi.cn/MyImages/2010-10/2780e121-e692-49cc-a6aa-fd79ecf51a5a.mp3");
                mediaPlayer = MediaPlayer.create(FirstActivity.this,uri);
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.setVideoPath("/sdcard/laonanhai.mp4");
                //videoView.setVideoURI(Uri.parse("http://pan.baidu.com/s/1c13vmSS"));
                videoView.start();
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstActivity.this,SecondActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        videoView.stopPlayback();
    }
}
