package com.example.hasee.news.di.component;


import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.module.ActivityModule;
import com.example.hasee.common.di.scope.ActivityScope;

import dagger.Component;

/**
 * ActivityScope 保证区域 的唯一性
 * AppComponent  全量的Component 提供 数据和网络帮助类的实例桥梁
 * ActivityModule 对应 activity
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface NewsActivityComponent {


}
