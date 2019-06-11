package com.example.hasee.common.base.mvp;

/**
 * Presenter基类
 * @param <V>
 */
public interface IBasePresenter<V extends IBaseView> {

    /**
     * 绑定view
     * @param view
     */
    void attachView(V view);


    /**
     * 解绑view
     */
    void detachView();
}
