package com.example.hasee.login.di.component;

import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.module.ActivityModule;
import com.example.hasee.common.di.module.FragmentModule;
import com.example.hasee.common.di.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(dependencies =AppComponent.class, modules = FragmentModule.class)
public interface LoginFragmentComponent {
}
