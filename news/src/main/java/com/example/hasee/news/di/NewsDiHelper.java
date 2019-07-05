package com.example.hasee.news.di;

import com.example.hasee.common.base.app.AppContext;
import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.di.module.ActivityModule;
import com.example.hasee.common.di.module.FragmentModule;
import com.example.hasee.news.di.component.DaggerNewsActivityComponent;
import com.example.hasee.news.di.component.DaggerNewsFragmentComponent;
import com.example.hasee.news.di.component.NewsActivityComponent;
import com.example.hasee.news.di.component.NewsFragmentComponent;

public class NewsDiHelper {

    public static NewsActivityComponent getActivityComponent(ActivityModule activityModule) {
        return DaggerNewsActivityComponent.builder()
                .appComponent(((BaseApplication) AppContext.get()).getAppComponent())
                .activityModule(activityModule)
                .build();
    }

    public static NewsFragmentComponent getFragmentComponent(FragmentModule fragmentModule) {
        return DaggerNewsFragmentComponent.builder()
                .appComponent(((BaseApplication) AppContext.get()).getAppComponent())
                .fragmentModule(fragmentModule)
                .build();

    }

}
