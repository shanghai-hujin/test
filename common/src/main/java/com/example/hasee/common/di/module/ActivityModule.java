package com.example.hasee.common.di.module;

import android.app.Activity;

import com.example.hasee.common.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private Activity mActivity;


    public ActivityModule(Activity mActivity) {
        this.mActivity = mActivity;
    }


    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return mActivity;
    }
}
