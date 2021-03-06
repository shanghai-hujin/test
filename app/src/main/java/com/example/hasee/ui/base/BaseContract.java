package com.example.hasee.ui.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 15:21
 */

public interface BaseContract {

    interface BasePresenter<T extends BaseContract.BaseView> {
        /**
         * @param view  绑定view
         */
        void attachView(T view);

        /**
         * 回收View
         */
        void detachView();
    }


    interface BaseView{


        /**
         * 显示进度中
         */
        void showLoading();

        /**
         * 显示请求成功
         */
        void showSuccess(String str);

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

        /**
         * 绑定生命周期
         *
         * @param <T>
         * @return
         */
        <T> LifecycleTransformer<T> bindToLife();

        /**
         * Show error message
         *
         * @param errorMsg error message
         */
        void showErrorMsg(String errorMsg);

        /**
         * Show error
         */
        void showError();
    }
}
