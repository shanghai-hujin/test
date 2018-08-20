package com.example.hasee.di.component;

import com.example.hasee.ui.news.NewsFragment;

import dagger.Component;

/**
 * Demo ${CLASS}
 *  提供网络请求的http
 * @author hu
 * @date 2018/8/20 09:53
 */
@Component(dependencies = ApplicationComponent.class)
public interface HttpComponent {

    void inject(NewsFragment newsFragment);
}
