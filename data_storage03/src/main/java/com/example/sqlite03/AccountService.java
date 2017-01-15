package com.example.sqlite03;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jackie.entity.Account;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 账号服务类   CRUD
 * Created by baigu on 2016/5/19.
 */
public class AccountService {

    private SQLiteHelper sqLiteHelper;

    public AccountService(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    //封装一个保存方法
    public void save(Account account) {
        SQLiteDatabase sqLiteDatabase = null;
        String sql = "insert into acc (lname,pwd,created_time) values (?,?,?)";
        Object[] params = {account.getLname(), account.getPwd(), account.getCreated_time().getTime()};
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        sqLiteDatabase.execSQL(sql, params);
    }

    //封装一个查找
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();

        Cursor cursor = null;
        SQLiteDatabase sqLiteDatabase = null;
        String sql = "select * from acc";
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();
        cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            Account account = new Account();
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String lname = cursor.getString(cursor.getColumnIndex("lname"));
            String pwd = cursor.getString(cursor.getColumnIndex("pwd"));
            long ms = cursor.getLong(cursor.getColumnIndex("created_time"));

            account.setId(id);
            account.setLname(lname);
            account.setPwd(pwd);
            if (ms > 0) {
                account.setCreated_time(new Date(ms));
            }
            accounts.add(account);
        }
        return accounts;
    }
}
