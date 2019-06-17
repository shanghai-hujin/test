package com.example.hasee.login.di;

import com.example.hasee.common.base.app.AppContext;
import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.di.module.ActivityModule;
import com.example.hasee.common.di.module.FragmentModule;
import com.example.hasee.login.di.component.DaggerLoginActivityComponent;
import com.example.hasee.login.di.component.DaggerLoginFragmentComponent;
import com.example.hasee.login.di.component.LoginActivityComponent;
import com.example.hasee.login.di.component.LoginFragmentComponent;

/**
 * 统一管理di提供的
 */
public class LoginDiHelper {

    public static LoginActivityComponent getActivityComponent(ActivityModule activityModule) {
        return DaggerLoginActivityComponent.builder().appComponent(((BaseApplication) AppContext.get()).getAppComponent())
                .activityModule(activityModule)
                .build();
    }

    public static LoginFragmentComponent getFragmentComponent(FragmentModule fragmentModule) {
        return DaggerLoginFragmentComponent.builder()
                .appComponent(((BaseApplication) AppContext.get()).getAppComponent())
                .build();

    }
}
