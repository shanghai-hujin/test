package com.example.hasee.login.di.component;

import com.example.hasee.common.di.component.AppComponent;
import com.example.hasee.common.di.module.ActivityModule;
import com.example.hasee.common.di.scope.ActivityScope;
import com.example.hasee.login.MainLoginActivity;

import javax.inject.Inject;

import dagger.Component;


@ActivityScope
@Component(dependencies =AppComponent.class, modules = ActivityModule.class)
public interface LoginActivityComponent {

    void inject(MainLoginActivity mainLoginActivity);

}
