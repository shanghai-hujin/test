package com.example.hasee.ui;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hasee.common.base.InitializeService;
import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.net.IDataHelper;


/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/28 16:12
 */

public class MyApplication extends BaseApplication {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
     *
     * @return
     */
    @Override
    public IDataHelper.NetConfig getNewConfig() {
        return new IDataHelper.NetConfig().setBaseURL("");
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