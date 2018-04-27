package com.example.hasee.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BasePresenter;

public class MainActivity extends BaseActivity<BasePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public int getContentLayout() {
        return 0;
    }


    @Override
    public void initData() {

    }
}
