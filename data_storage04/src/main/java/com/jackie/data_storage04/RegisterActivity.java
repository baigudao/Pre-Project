package com.jackie.data_storage04;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/28.
 */
public class RegisterActivity extends Activity {

    private EditText et_name;
    private EditText et_pwd;
    private Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        et_name = (EditText)findViewById(R.id.et_name);
        et_pwd = (EditText)findViewById(R.id.et_password);
        btn_register = (Button)findViewById(R.id.btn_register);

        String lname = et_name.getText().toString();
        String pwd = et_pwd.getText().toString();
        Date date = new Date();

        Account account = new Account();
        account.setLname(lname);
        account.setPwd(et_pwd.toString());
        account.setDate(date);




    }
}
