package com.jackie.data_storage04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/28.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private Context context;

    private static final String DB_NAME = "account.db";
    private static final int DB_VERSION = 1;

    private String TABLE_ACC = "create table acc (id integer primary key autoincreament ,lname varchar(32) ,pwd varchar (32),create_time real(32))";

    public SQLiteHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION );
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        Toast.makeText(context, "SQLiteHelper中的onCreate方法", Toast.LENGTH_SHORT).show();
        db.execSQL(TABLE_ACC);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Toast.makeText(context, "SQLiteHelper中的onUpgrade方法", Toast.LENGTH_SHORT).show();
    }
}
