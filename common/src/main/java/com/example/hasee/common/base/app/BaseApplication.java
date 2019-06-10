package com.example.hasee.common.base.app;

import android.app.Application;
import android.content.res.Configuration;

import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.component.DaggerAppComponent;
import com.example.hasee.common.net.IDataHelper;

public class BaseApplication extends Application implements IBaseApplication{
    private XAppDelegate xAppDelegate;


    @Override
    public void onCreate() {
        super.onCreate();
        xAppDelegate = new XAppDelegateImpl(this)
                .netConfig(getNewConfig());
        //oncreate交给XAppDelegateImpl实现
        xAppDelegate.onCreate();

    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        xAppDelegate.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        xAppDelegate.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        xAppDelegate.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        xAppDelegate.onConfigurationChanged(newConfig);
    }


    /**
     * 返回XAppDelegate
     * @return
     */
    public XAppDelegate getXAppDelegate() {
        return xAppDelegate;
    }

    /**
     * 返回AppComponent
     * @return
     */
    @Override
    public AppComponent getAppComponent() {
        return xAppDelegate.getAppComponent();
    }


    @Override
    public IDataHelper.NetConfig getNewConfig() {
        return null;
    }


    @Override
    public DaggerAppComponent.Builder getAppComponentBuilder() {
        return xAppDelegate.getAppComponentBuilder();
    }
}
