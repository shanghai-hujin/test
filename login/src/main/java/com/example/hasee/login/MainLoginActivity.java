package com.example.hasee.login;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.common.base.mvp.XDaggerActivity;
import com.example.hasee.common.base.ui.BaseActivity;

public class MainLoginActivity<LoginPresenter> extends XDaggerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }


}
