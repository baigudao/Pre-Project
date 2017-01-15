package com.jackie.android.async.http.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jackie.android.async.http.R;
import com.jackie.android.async.http.util.AsyncHttpHelper;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;


/**
 * Created by Administrator on 2016/11/30.
 */
public class MainActivity extends Activity {

    private Button button_register;
    private EditText editText_lname;
    private EditText editText_phone_number;
    private EditText editText_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_register = (Button) findViewById(R.id.btn_register);

        editText_lname = (EditText) findViewById(R.id.name);
        editText_phone_number = (EditText) findViewById(R.id.phone);
        editText_password = (EditText) findViewById(R.id.password);

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lname = editText_lname.getText().toString();
                String phone_number = editText_phone_number.getText().toString();
                String password = editText_password.getText().toString();

                String url = "http://192.168.1.100:8080/waimai/register";

                RequestParams params = new RequestParams();
                params.put("lname", lname);
                params.put("phone_number", phone_number);
                params.put("password", password);

                AsyncHttpHelper.getClient().post(url, params, new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onSuccess(int i, Header[] headers, String s) {
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
