package com.example.hasee.common.di.module;

import android.support.annotation.NonNull;

import com.example.hasee.common.base.app.BaseApplication;

import java.util.Random;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 提供application实例
 */
@Module
public class AppModule {
    private final BaseApplication application;

    public AppModule(BaseApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    BaseApplication provideApplicationContext() {
        return application;
    }


    @Provides
    @NonNull
    @Singleton
    public Random random() {
        return new Random();
    }
}
