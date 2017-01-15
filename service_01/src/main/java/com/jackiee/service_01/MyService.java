package com.jackiee.service_01;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by baigu on 2016/5/5.
 */
public class MyService extends Service {

    private MediaPlayer mediaPlayer;
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(MyService.this, "oncreate", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(this, R.raw.dahantianzi);
        mediaPlayer.setLooping(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(MyService.this, "onbind", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        return myBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(MyService.this, "ondestory", Toast.LENGTH_SHORT).show();
        mediaPlayer.release();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(MyService.this, "onstartcommand", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(MyService.this, "onunbind", Toast.LENGTH_SHORT).show();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        return super.onUnbind(intent);
    }

    class MyBinder extends Binder {
        public void test(Context context) {
            Toast.makeText(context, "test方法正在执行", Toast.LENGTH_SHORT).show();
        }
    }
}
