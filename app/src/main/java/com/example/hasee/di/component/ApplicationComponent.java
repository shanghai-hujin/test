package com.example.hasee.di.component;

import com.example.hasee.di.module.ApplicationModule;
import com.example.hasee.di.module.HttpModule;
import com.example.hasee.di.scope.ActivityScope;
import com.example.hasee.http.OtherHttpApi;

import dagger.Component;

/**
 * Created by HASEE on 2018/8/19.
 */
@ActivityScope
@Component(modules = {ApplicationModule.class, HttpModule.class})
public interface ApplicationComponent {

    /**
     *说明:用来连接被inject标记的OtherHttpApi
     *作者:hujin
     *添加时间:2018/8/20 10:35
     *修改人:hujin
     *修改时间:2018/8/20 10:35
     */
    OtherHttpApi getOtherHttpApi();

}
