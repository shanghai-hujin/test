package com.example.hasee.common.base.app;

import android.app.Activity;
import android.app.Application;
import android.content.res.Configuration;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hasee.common.base.ui.AppManager;
import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.component.DaggerAppComponent;
import com.example.hasee.common.di.module.AppModule;
import com.example.hasee.common.di.module.DataModule;
import com.example.hasee.common.net.IDataHelper;
import com.example.hasee.common.utils.AppUtil;
import com.example.hasee.common.utils.ScreenUtil;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;


public class XAppDelegateImpl implements XAppDelegate {
    private Application application;

    private DaggerAppComponent.Builder builder;

    private IDataHelper.NetConfig mNetConfig;

    public XAppDelegateImpl(Application application) {
        this.application = application;
    }


    @Override
    public void onCreate() {
        AppContext.init(application);//保存全局的application
        AppUtil.syncIsDebug(application.getApplicationContext());//判断是否是debug模式
        ScreenUtil.init(application);    // init 像素工具类
        if (AppUtil.isDebug()) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(application); // 尽可能早，推荐在Application中初始化
        //目前没有接阿里路由，后期再说吧
        application.registerActivityLifecycleCallbacks(new LifeBackgroundCallbacks());


        /**
         * 日志系统
         */
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });


    }

    /**
     * 管理activity
     */
    public class LifeBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            AppManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getInstance().removeActivity(activity);
        }
    }

    @Override
    public void onTerminate() {
        AsLibUtil.onTerminate(application);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        AsLibUtil.onConfigurationChanged(application, newConfig);
    }

    @Override
    public void onLowMemory() {
        AsLibUtil.onLowMemoryAsLibrary(application);
    }

    @Override
    public void onTrimMemory(int level) {
        AsLibUtil.onTrimMemoryAsLibrary(application, level);
    }

    @Override
    public AppComponent getAppComponent() {
        if (builder == null)
            builder = getAppComponentBuilder();
        return builder.build();
    }

    @Override
    public DaggerAppComponent.Builder getAppComponentBuilder() {
        return builder = DaggerAppComponent.builder()
                .dataModule(new DataModule(mNetConfig))
                .appModule(new AppModule((BaseApplication) application));
    }

    @Override
    public XAppDelegate netConfig(IDataHelper.NetConfig netConfig) {
        mNetConfig = netConfig;
        return this;
    }
}
