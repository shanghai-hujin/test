package com.example.hasee.loginmodule;

import android.util.Log;

import com.example.hasee.login.LoginApplication;

/**
 * 技术相关的组件的初始化
 * 只会单独运行时候 会初始化技术相关组件，不会打包在最合的代码里面
 * 造成多次运行
 */
public class LoginMoudleApplication extends LoginApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("LoginMoudleApplication", "登陆模块 app中 onCreate");

    }
}
