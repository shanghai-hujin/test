package com.example.hasee.common.base.mvp;

public class BasePresenter<V extends IBaseView> implements IBasePresenter<V> {
    /**
     * 模块隔离了，不在使用protected获取mView
     */
    private V mView;

    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    public V getV() {
        if(mView == null){
            throw new IllegalStateException("mvp : 是否绑定了view");
        }
        return mView;
    }
}
