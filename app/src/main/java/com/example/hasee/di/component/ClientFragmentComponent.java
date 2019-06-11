package com.example.hasee.di.component;

import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.module.FragmentModule;

import dagger.Component;

/**
 * fragment在这里注入
 */
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface ClientFragmentComponent {
}
