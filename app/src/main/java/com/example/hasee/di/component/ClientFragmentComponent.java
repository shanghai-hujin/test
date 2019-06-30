package com.example.hasee.di.component;

import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.module.FragmentModule;
import com.example.hasee.common.di.scope.FragmentScope;

import dagger.Component;

/**
 * fragment在这里注入
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface ClientFragmentComponent {
    //void inject(MyFragment myFragment);
}
