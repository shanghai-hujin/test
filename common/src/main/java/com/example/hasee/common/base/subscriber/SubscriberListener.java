package com.example.hasee.common.base.subscriber;

import com.example.hasee.common.net.NetError;

import retrofit2.HttpException;

public abstract class SubscriberListener<T> {
    /**
     * 成功
     * @param response
     */
    public abstract void onSuccess(T response);

    /**
     * 最终错误处理
     * @param error
     */
    public abstract void onFail(NetError error);

    /**
     * 抛出异常,还是在onFail中处理
     * @param e
     */
    public abstract void onError(Throwable e);

    /**
     * end
     */
    public void onCompleted() {
    }

    /**
     * begin
     */
    public void onStart() {
    }

    /**
     * 是否需要重新登录
     * @return
     * @param httpException
     */
    public boolean isCheckReLogin(HttpException httpException) {
        return false;
    }

    /**
     * 重新登录处理
     * @param errorCode
     * @param errorMsg
     */
    public abstract void checkReLogin(String errorCode,String errorMsg);
}
