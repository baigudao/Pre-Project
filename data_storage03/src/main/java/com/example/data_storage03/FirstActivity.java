package com.example.data_storage03;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sqlite03.AccountService;
import com.jackie.entity.Account;

import java.util.Date;
import java.util.List;

/**
 * Created by baigu on 2016/5/20.
 */
public class FirstActivity extends Activity {

    private EditText editText_name;
    private EditText editText_pwd;
    private Button button_save;
    private Button button_show;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        editText_name = (EditText)findViewById(R.id.et);
        editText_pwd = (EditText)findViewById(R.id.et_pwd);
        button_save = (Button)findViewById(R.id.btn);
        button_show = (Button)findViewById(R.id.btn_show);
        textView = (TextView)findViewById(R.id.tv_show);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lname = editText_name.getText().toString();
                String pwd = editText_pwd.getText().toString();
                long ms = System.currentTimeMillis();
                Date curr_time = new Date(ms);

                Account account = new Account();
                account.setLname(lname);
                account.setPwd(pwd);
                account.setCreated_time(curr_time);

                AccountService accountService = new AccountService(FirstActivity.this);
                accountService.save(account);
            }
        });

        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccountService accountService = new AccountService(FirstActivity.this);
                List<Account> accounts = accountService.findAll();

                textView.setText(accounts.toString());
                //Toast.makeText(FirstActivity.this, accounts.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
