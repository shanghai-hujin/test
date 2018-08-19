package com.example.hasee.di.module;

import com.example.hasee.di.scope.ActivityScope;
import com.example.hasee.ui.news.NewsContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by HASEE on 2018/8/19.
 */
@Module
public class NewsModule {
    private NewsContract.NewsView newsView;


    public NewsModule(NewsContract.NewsView newsView) {
        this.newsView = newsView;
    }

    @Provides
    @ActivityScope
    public NewsContract.NewsView providesView (){
        return newsView;
    }
}
