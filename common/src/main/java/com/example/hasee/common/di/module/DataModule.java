package com.example.hasee.common.di.module;



import com.example.hasee.common.base.app.BaseApplication;
import com.example.hasee.common.data.DBHelper;
import com.example.hasee.common.net.HttpHelper;
import com.example.hasee.common.net.IDataHelper;
import com.example.hasee.common.net.cache.ICache;
import com.example.hasee.common.net.cache.MemoryCache;
import com.example.hasee.common.utils.FileUtil;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

@Module
public class DataModule {

    IDataHelper.NetConfig netConfig;


    public DataModule(IDataHelper.NetConfig netConfig) {
        this.netConfig = netConfig;
    }

    /**
     * 提供httphelp类
     * @param application
     * @param iCache
     * @return
     */
    @Provides
    @Singleton
    HttpHelper provideHttpHelper(BaseApplication application, ICache iCache) {
        HttpHelper httpHelper = new HttpHelper(application,iCache);
        if (netConfig == null)
            netConfig = new IDataHelper.NetConfig();
        httpHelper.initConfig(netConfig);
        return httpHelper;
    }

    /**
     *
     * @param application
     * @param iCache
     * @return
     */
    @Provides
    @Singleton
    DBHelper provideDatabaseHelper(BaseApplication application, ICache iCache) {
        return new DBHelper(application,iCache);
    }



    @Singleton
    @Provides
    ICache provideLruCache() {
        return MemoryCache.getInstance();
    }


    /**
     * 提供 {@link RxCache}
     *
     * @param cacheDirectory RxCache缓存路径
     * @return
     */
    @Singleton
    @Provides
    RxCache provideRxCache(@Named("RxCacheDirectory") File cacheDirectory) {
        RxCache.Builder builder = new RxCache.Builder();
        return builder
                .persistence(cacheDirectory, new GsonSpeaker());
    }

    /**
     * 需要单独给 {@link RxCache} 提供缓存路径
     *
     * @return
     */
    @Singleton
    @Provides
    @Named("RxCacheDirectory")
    File provideRxCacheDirectory(BaseApplication application) {
        File cacheDirectory = new File(FileUtil.getCacheDirectory(application), "RxCache");

        return FileUtil.makeDirs(cacheDirectory);
    }

}
