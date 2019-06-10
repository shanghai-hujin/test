package com.example.hasee.common.di.component;

import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.data.DBHelper;
import com.example.hasee.common.di.module.AppModule;
import com.example.hasee.common.di.module.DataModule;
import com.example.hasee.common.net.HttpHelper;
import com.example.hasee.common.net.cache.ICache;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Component;
import io.rx_cache2.internal.RxCache;

/**
 * 桥梁类
 */
@Singleton
@Component(modules = {DataModule.class,AppModule.class})
public interface AppComponent {

    /**
     * 提供app的context
     * @return
     */
    BaseApplication getContext();

    /**
     * 提供网络类
     * @return
     */
    HttpHelper httpHelper();

    /**
     * 缓存操作类
     * @return
     */
    ICache memoyCache();


    /**
     * 数据帮助类
     * @return
     */
    DBHelper dataHelper();

    /**
     * 随机数
     * @return
     */
    Random random();

    /**
     * rxcache 类
     * @return
     */
    RxCache getRxCache();

}
