package com.jackie.contacts_provider;

import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.RawContacts.Data;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13.
 */
public class MainActivity extends Activity {

    private Button button;
    private Button add;
    private ListView listView;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btn_load_contacts);
        add = (Button) findViewById(R.id.btn_add_contacts);
        listView = (ListView) findViewById(R.id.lv);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadContacts();
            }
        });

        // 为add按钮的单击事件绑定监听器
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContacts();
            }
        });

    }

    private void loadContacts() {

        List<String> lists = new ArrayList<>();

        Cursor cursor = null;
        try {
            // 查询联系人数据
            String[] projection = {Phone._ID, Phone.DISPLAY_NAME_PRIMARY,Phone.NUMBER};
            String sortOrder = Phone._ID + " DESC ";//降序排列
            cursor = getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    projection, null, null, sortOrder);
            while (cursor.moveToNext()) {
                // 获取联系人ID
                int id = cursor.getInt(cursor.getColumnIndex(Phone._ID));
                // 获取联系人姓名
                String displayName = cursor.getString(cursor.getColumnIndex(
                        Phone.DISPLAY_NAME));
                // 获取联系人手机号
                String number = cursor.getString(cursor.getColumnIndex(
                        Phone.NUMBER));
                lists.add(id + ":" + displayName + "\n" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lists);
        listView.setAdapter(adapter);
    }


    private void addContacts() {
        // 获取程序界面中的三个文本框的内容
        String name = ((EditText) findViewById(R.id.et_name))
                .getText().toString();
        String phone = ((EditText) findViewById(R.id.et_phone))
                .getText().toString();
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();
        // 向RawContacts.CONTENT_URI执行一个空值插入
        // 目的是获取系统返回的rawContactId
        Uri rawContactUri = getContentResolver().insert(
                ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        // 设置内容类型
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);
        // 设置联系人名字
        values.put(StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        getContentResolver().insert(android.provider.ContactsContract
                .Data.CONTENT_URI, values);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        // 设置联系人的电话号码
        values.put(Phone.NUMBER, phone);
        // 设置电话类型
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);
        // 向联系人电话号码URI添加电话号码
        getContentResolver().insert(android.provider.ContactsContract
                .Data.CONTENT_URI, values);
        values.clear();
        values.put(Data.RAW_CONTACT_ID, rawContactId);

        Toast.makeText(MainActivity.this, "联系人数据添加成功",
                Toast.LENGTH_SHORT).show();
    }
}


