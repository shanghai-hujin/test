package com.example.hasee.common.base.mvp;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * mvp的view基类
 */
public interface IBaseView {
    /**
     * Retrofit bind View Lifecycle
     * @param <T>   Response data
     * @return  Response data
     */
    <T> LifecycleTransformer<T> bindLifecycle() ;

    /**
     * get  View context
     * @return
     */
    Context getAContext() ;
}
