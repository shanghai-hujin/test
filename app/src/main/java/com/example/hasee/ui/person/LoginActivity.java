package com.example.hasee.ui.person;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.ui.base.IBase;

/**
 * Created by HASEE on 2018/4/30.
 */

public class LoginActivity<P extends LoginPresenter> extends BaseActivity
        implements PersonGroupView.LoginView{
    @Override
    public int getContentLayout() {
        return 0;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void loadData(String string) {

    }
}
