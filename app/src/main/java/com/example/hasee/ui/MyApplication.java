package com.example.hasee.ui;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.hasee.di.component.ApplicationComponent;
import com.example.hasee.di.component.DaggerApplicationComponent;
import com.example.hasee.di.module.ApplicationModule;
import com.example.hasee.di.module.HttpModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/28 16:12
 */

public class MyApplication extends LitePalApplication {

    private static MyApplication instance;

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initARouter();

        instance = this;

        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true;
            }
        });

        //初始化图片加载库
        Fresco.initialize(this);
        //数据库初始化
        LitePal.initialize(this);
        //初始化侧滑
        BGASwipeBackManager.getInstance().init(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .build();
    }

    /**
     * 是否是调试模式
     */
    private boolean isDebugARouter = true;
    /**
     *说明:初始化路由
     *作者:hujin
     *添加时间:2018/8/21 15:10
     *修改人:hujin
     *修改时间:2018/8/21 15:10
     */
    private void initARouter() {
        if(isDebugARouter){
            //打开打印
            ARouter.openLog();
            //打开调试模式
            ARouter.openDebug();
        }
        ARouter.init(this);
    }

    public static MyApplication getInstance() {
        return instance;
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