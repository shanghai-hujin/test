package com.example.hasee.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;

import org.jetbrains.annotations.NotNull;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 15:21
 */

public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> extends LifecycleObserver{
        /**
         * @param view  绑定view
         */
        void attachView(T view);




        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        void onCreate(@NotNull LifecycleOwner owner);

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void onStart(@NotNull LifecycleOwner owner);

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        void onResume(@NotNull LifecycleOwner owner);

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        void onPause(@NotNull LifecycleOwner owner);

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        void onStop(@NotNull LifecycleOwner owner);

        /**
         * 解除绑定
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        void onDestroy(@NotNull LifecycleOwner owner);


        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        void onLifecycleChanged(@NotNull LifecycleOwner owner,
                                @NotNull Lifecycle.Event event);
    }


    interface BaseView{


        /**
         * 显示进度中
         */
        void showLoading();

        /**
         * 显示请求成功
         */
        void showSuccess();

        /**
         * 失败重试
         */
        void showFaild();

        /**
         * 显示当前网络不可用
         */
        void showNoNet();

        /**
         * 重试
         */
        void onRetry();
    }
}
