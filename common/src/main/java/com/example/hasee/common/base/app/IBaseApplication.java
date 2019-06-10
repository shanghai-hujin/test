package com.example.hasee.common.base.app;

import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.component.DaggerAppComponent;
import com.example.hasee.common.net.IDataHelper;

public interface IBaseApplication {

    AppComponent getAppComponent();

    IDataHelper.NetConfig getNewConfig();

    DaggerAppComponent.Builder getAppComponentBuilder();
}
