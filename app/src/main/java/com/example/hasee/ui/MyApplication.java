package com.example.hasee.ui;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/28 16:12
 */

public class MyApplication extends LitePalApplication {

    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.DEBUG;
            }
        });

        //初始化图片加载库
        Fresco.initialize(this);
        //数据库初始化
        LitePal.initialize(this);
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
