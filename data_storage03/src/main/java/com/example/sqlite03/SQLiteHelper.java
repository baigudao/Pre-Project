package com.example.sqlite03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.data_storage03.BuildConfig;

/**
 * Created by baigu on 2016/5/19.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_ACC = "create table acc " +
            "(id integer primary key autoincrement, " +
            "lname varchar(32) ," +
            "pwd varchar(32) ," +
            "created_time real(32))"; //建表语句

    //1，使用者传来的上下文
    //2, 数据库名
    //3，游标工厂，可以使用系统内置的游标工厂，传一个null
    //4，数据库的版本
    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    //第一次创建数据库就被调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL(TABLE_ACC);
        if (BuildConfig.DEBUG){
            Log.d("数据库","调用的是oncreate方法==============================");
        }
    }


    //数据库版本发生变化时调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (BuildConfig.DEBUG){
            Log.d("数据库","调用的是onupgrade方法-------------------------------");
        }
    }
}
