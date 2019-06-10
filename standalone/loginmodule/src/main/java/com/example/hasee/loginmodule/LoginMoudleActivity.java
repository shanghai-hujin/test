package com.example.hasee.loginmodule;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.hasee.login.MainLoginActivity;

public class LoginMoudleActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_moudle);
        Intent intent = new Intent();
        intent.setClass(this,MainLoginActivity.class);
        startActivity(intent);
    }
}
