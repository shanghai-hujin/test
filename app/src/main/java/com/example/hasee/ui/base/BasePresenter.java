package com.example.hasee.ui.base;

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

    @Override
    /**
     * 绑定view
     */
    public void attachView(T view) {
        this.view = view;
    }

    public void detachView(){
        if(view != null){
            view = null;
        }
    }

}
