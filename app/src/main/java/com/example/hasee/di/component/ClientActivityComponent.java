package com.example.hasee.di.component;


import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.module.ActivityModule;

import dagger.Component;

/**
 * activity的注解使用,限定Context的范围,以及依赖注入的范围
 */
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ClientActivityComponent {
}
