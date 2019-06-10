package com.example.hasee.common.base.app;

import android.content.res.Configuration;

import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.component.DaggerAppComponent;
import com.example.hasee.common.net.IDataHelper;

public interface XAppDelegate {
    void onCreate();

    void onTerminate();

    void onConfigurationChanged(Configuration newConfig);

    void onLowMemory();

    void onTrimMemory(int level);

    AppComponent getAppComponent();


    /**
     * 这个是编译生成的
     * 爆红先不用管
     *
     * @return
     */
    DaggerAppComponent.Builder getAppComponentBuilder();

    XAppDelegate netConfig(IDataHelper.NetConfig netConfig) ;

}
