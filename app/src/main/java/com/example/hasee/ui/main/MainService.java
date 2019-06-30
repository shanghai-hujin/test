package com.example.hasee.ui.main;

import com.example.hasee.common.net.HttpHelper;

import javax.inject.Inject;

public class MainService implements MainContract.MainModule {

    private HttpHelper httpHelper;

    /**
     *说明:被inject标记了，会被注入到被 component标记的目标
     * 构造函数中的参数由 module来提供
     * 然后在 component标记的目标中使用  @Inject标记后，直接使用该对象
     * 可以多个一起
     *作者:hujin
     *添加时间:2018/8/20 11:07
     *修改人:hujin
     *修改时间:2018/8/20 11:07
     */
    @Inject
    public MainService(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }
}
