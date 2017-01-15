package com.jackie.data_storage04;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/28.
 */
public class AccountServer {

    private SQLiteHelper sqLiteHelper ;

    public AccountServer (Context context){
        sqLiteHelper = new SQLiteHelper(context);
    }

    public void save (Account account){
        SQLiteDatabase sqLiteDatabase = null;
        String sql = "insert into acc (lname,pwd ,create_time) values (? ,? ,?)";

        Object [] objects = {account.getLname(),account.getPwd(),account.getDate().getTime()};

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        sqLiteDatabase.execSQL(sql,objects);

    }

    public List<Account> FindAll (){
        List<Account> list = new ArrayList<>();

        return list;
    }

}
