package com.example.hasee.ui.base;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 15:22
 */

public class BasePresenter <T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    public T getMvpView() {
        return mView;
    }

    protected   T mView;

    @Override
    /**
     * 绑定view
     */
    public void attachView(T view) {
        this.mView = view;
    }

    public void detachView(){
        if(mView != null){
            mView = null;
        }
    }

}
