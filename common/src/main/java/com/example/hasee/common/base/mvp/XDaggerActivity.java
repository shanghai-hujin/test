package com.example.hasee.common.base.mvp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.hasee.common.base.ui.BaseActivity;
import com.example.hasee.common.di.module.ActivityModule;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

/**
 * mvp dagger2 的基类
 */
public abstract class XDaggerActivity<T extends IBasePresenter> extends BaseActivity
        implements IBaseView, ISupportDagger {

    @Inject
    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initInject(savedInstanceState);
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public <E> LifecycleTransformer<E> bindLifecycle() {
        return this.<E>bindToLifecycle();
    }

    @Override
    public Context getAContext() {
        return this;
    }

    /**
     * 抽取 返回的 module
     * @return
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null){
            mPresenter.detachView();
        }
    }


}
