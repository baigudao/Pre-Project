package com.jackie.data_storage06.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/10/30.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {


    private static final String CREATE_BOOK = "create table book (\n" +
            "\tid    integer    primary key   autoincrement,\n" +
            "\tauthor  text,\n" +
            "\tprice   real,\n" +
            "\tpages   integer,\n" +
            "\tname    text\n" +
            ")";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
