package com.example.hasee.di.module;

import android.content.Context;

import com.example.hasee.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HASEE on 2018/8/19.
 */
@Module
public class ApplicationModule {

    private  Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    @ActivityScope
    public Context providesContext(){
        return context;
    }
}
