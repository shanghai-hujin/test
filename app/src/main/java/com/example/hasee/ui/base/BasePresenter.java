package com.example.hasee.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.CallSuper;
import android.support.annotation.MainThread;

import com.example.hasee.http.RxLifecycleUtils;
import com.uber.autodispose.AutoDisposeConverter;

import org.jetbrains.annotations.NotNull;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 15:22
 */

public class BasePresenter <T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    public T getMvpView() {
        return view;
    }

    private  T view;
    private LifecycleOwner lifecycleOwner;

    @Override
    /**
     * 绑定view
     */
    public void attachView(T view) {
        this.view = view;
    }



    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        if (null == lifecycleOwner) {
            throw new NullPointerException("lifecycleOwner == null");
        }
        return RxLifecycleUtils.bindLifecycle(lifecycleOwner);
    }

    @CallSuper
    @MainThread
    @Override
    public void onCreate(@NotNull LifecycleOwner owner) {
        this.lifecycleOwner = owner;

    }

    @CallSuper
    @MainThread
    @Override
    public void onStart(@NotNull LifecycleOwner owner) {

    }

    @CallSuper
    @MainThread
    @Override
    public void onResume(@NotNull LifecycleOwner owner) {

    }

    @CallSuper
    @MainThread
    @Override
    public void onPause(@NotNull LifecycleOwner owner) {

    }

    @CallSuper
    @MainThread
    @Override
    public void onStop(@NotNull LifecycleOwner owner) {

    }

    @CallSuper
    @MainThread
    @Override
    public void onDestroy(@NotNull LifecycleOwner owner) {
        this.view = null;
    }

    @CallSuper
    @MainThread
    @Override
    public void onLifecycleChanged(@NotNull LifecycleOwner owner, @NotNull Lifecycle.Event event) {

    }
}
