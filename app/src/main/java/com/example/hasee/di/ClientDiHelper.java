package com.example.hasee.di;

import com.example.hasee.common.base.app.AppContext;
import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.di.module.ActivityModule;
import com.example.hasee.common.di.module.FragmentModule;
import com.example.hasee.di.component.ClientActivityComponent;
import com.example.hasee.di.component.ClientFragmentComponent;
import com.example.hasee.di.component.DaggerClientActivityComponent;
import com.example.hasee.di.component.DaggerClientFragmentComponent;


public class ClientDiHelper {
    public static ClientActivityComponent getActivityComponent(ActivityModule activityModule) {
        return DaggerClientActivityComponent.builder()
                .appComponent(((BaseApplication) AppContext.get()).getAppComponent())
                .activityModule(activityModule)
                .build();
    }

    public static ClientFragmentComponent getFragmentComponent(FragmentModule fragmentModule) {
        return DaggerClientFragmentComponent.builder()
                .appComponent(((BaseApplication) AppContext.get()).getAppComponent())
                .fragmentModule(fragmentModule)
                .build();
    }
}
