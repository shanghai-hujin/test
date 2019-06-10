package com.example.hasee.ui;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hasee.common.Constants;
import com.example.hasee.common.base.InitializeService;
import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.net.IDataHelper;
import com.example.hasee.di.component.ApplicationComponent;
import com.example.hasee.di.component.DaggerApplicationComponent;
import com.example.hasee.di.module.ApplicationModule;
import com.example.hasee.di.module.HttpModule;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import okhttp3.Interceptor;
import retrofit2.Converter;


/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/28 16:12
 */

public class MyApplication extends BaseApplication {

    private static MyApplication instance;

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        /**
         * 日志系统
         */
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });

        ARouter.getInstance().inject(this);
        //耗时并不重要的初始化
        InitializeService.start(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     * 必须重新设置网络config
     * @return
     */
    @Override
    public IDataHelper.NetConfig getNewConfig() {
        return new IDataHelper.NetConfig();
    }

    /**
     * 终止方法中destory路由
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        ARouter.getInstance().destroy();
    }
}