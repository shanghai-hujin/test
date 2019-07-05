package com.example.hasee.news.di.component;


import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.module.FragmentModule;
import com.example.hasee.common.di.scope.FragmentScope;
import com.example.hasee.news.ui.main.NewsFragment;

import dagger.Component;

/**
 * FragmentScope 保证区域 的唯一性
 * AppComponent  全量的Component 提供 数据和网络帮助类的实例桥梁
 * ActivityModule 对应 fragment
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface NewsFragmentComponent {

    void inject(NewsFragment newsFragment);

}
