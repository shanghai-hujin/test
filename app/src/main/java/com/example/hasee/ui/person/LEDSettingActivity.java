package com.example.hasee.ui.person;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.hasee.R;
import com.example.hasee.di.component.ApplicationComponent;
import com.example.hasee.http.ComPath;
import com.example.hasee.ui.base.BaseActivity;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/21 16:05
 */
@Route( path = ComPath.PATH_LEDSETTINGACTIVITY)
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
    public void initInjector(ApplicationComponent applicationComponent) {

    }

}
