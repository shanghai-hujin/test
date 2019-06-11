package com.example.hasee.common.base.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.hasee.common.base.ui.BaseFragment;
import com.example.hasee.common.di.module.FragmentModule;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

public abstract class XDaggerFragment<T extends IBasePresenter> extends BaseFragment
    implements IBaseView, ISupportDagger{


    @Inject
    protected T mPresenter;

    @Override
    public <E> LifecycleTransformer<E> bindLifecycle() {
        return this.<E>bindToLifecycle();
    }

    @Override
    public Context getAContext() {
        return _mActivity;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject(savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null)
            mPresenter.detachView();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }
}
