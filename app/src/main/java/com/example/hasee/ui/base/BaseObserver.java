package com.example.hasee.ui.base;

import android.text.TextUtils;

import com.example.hasee.R;
import com.example.hasee.ui.MyApplication;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.orhanobut.logger.Logger;

import io.reactivex.observers.ResourceObserver;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/2 17:36
 */

public abstract class BaseObserver<T> extends ResourceObserver<T> {
    private BaseContract.BaseView baseView;
    private String mErrorMsg;
    private boolean isShowError = true;

    public BaseObserver(BaseContract.BaseView view){
        this.baseView = view;
    }

    protected BaseObserver(BaseContract.BaseView view, String errorMsg){
        this.baseView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(BaseContract.BaseView view, boolean isShowError){
        this.baseView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(BaseContract.BaseView view, String errorMsg, boolean isShowError){
        this.baseView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }



    @Override
    public void onError(Throwable e) {
        if (baseView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            baseView.showErrorMsg(mErrorMsg);
        }  else if (e instanceof HttpException) {
            baseView.showErrorMsg(MyApplication.getInstance().getString(R.string.http_error));
        } else {
            baseView.showErrorMsg(MyApplication.getInstance().getString(R.string.unKnown_error));
            Logger.d(e.toString());
        }
        if (isShowError) {
            baseView.showError();
        }
    }

    @Override
    public void onComplete() {

    }
}
