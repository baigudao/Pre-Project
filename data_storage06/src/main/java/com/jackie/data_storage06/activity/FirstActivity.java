package com.jackie.data_storage06.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jackie.data_storage06.db.MyDatabaseHelper;
import com.jackie.data_storage06.R;

/**
 * Created by Administrator on 2016/10/30.
 */
public class FirstActivity extends Activity {

    private Button button_create;
    private Button button_add;
    private Button button_query;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_first);

        button_create = (Button) this.findViewById(R.id.btn_create);
        button_add = (Button)findViewById(R.id.btn_add);
        button_query = (Button)findViewById(R.id.btn_query);

        myDatabaseHelper = new MyDatabaseHelper(this, "book.db", null, 1);

        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabaseHelper.getWritableDatabase();
            }
        });

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("author","曹雪芹");
                values.put("name","红楼梦");
                values.put("price",88.8);
                values.put("pages",500);
                sqLiteDatabase.insert("book",null,values);
            }
        });

        button_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getReadableDatabase();
                Cursor cursor = sqLiteDatabase.query("book",null,null,null,null,null,null);

                while (cursor.moveToNext()){
                    String author = cursor.getString(cursor.getColumnIndex("author"));
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    double price = cursor.getDouble(cursor.getColumnIndex("price"));
                    int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                    Toast.makeText(FirstActivity.this, author + name + price + pages, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
