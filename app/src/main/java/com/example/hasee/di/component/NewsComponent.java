package com.example.hasee.di.component;

import com.example.hasee.di.module.NewsModule;
import com.example.hasee.ui.news.NewsFragment;

import javax.inject.Inject;

import dagger.Component;

/**
 * Created by HASEE on 2018/8/19.
 */
@Component(modules = NewsModule.class)
public interface NewsComponent {
    void inject(NewsFragment newsFragment);
}
