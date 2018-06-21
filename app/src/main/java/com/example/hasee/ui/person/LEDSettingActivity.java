package com.example.hasee.ui.person;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseActivity;
import com.example.hasee.ui.base.BaseContract;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/21 16:05
 */

public class LEDSettingActivity extends BaseActivity{
    @Override
    public int getContentLayout() {
        return R.layout.activity_ledsetting;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }

    @Override
    public BaseContract.BasePresenter createPresenter() {
        return null;
    }
}
