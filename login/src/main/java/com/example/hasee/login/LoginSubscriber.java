package com.example.hasee.login;

import android.util.Log;

import com.example.hasee.common.net.NetError;
import com.example.hasee.common.net.subscriber.BaseSubscriber;
import com.example.hasee.common.net.subscriber.IResponse;
import com.example.hasee.common.net.subscriber.SubscriberListener;

/**
 * 非必要实现
 */
public class LoginSubscriber<T extends IResponse> extends BaseSubscriber<T> {


    public LoginSubscriber(SubscriberListener mSubscriberOnNextListener) {
        super(mSubscriberOnNextListener);
    }


    /**
     * 将data数据剥离处出来
     * @param response
     */
    @Override
    public void onNext(T response) {
        if (mSubscriberOnNextListener != null) {
            if (response != null && response.isSuccess()) {
                mSubscriberOnNextListener.onSuccess(response.getData());
            } else {
                if (response.checkReLogin())
                    mSubscriberOnNextListener.checkReLogin("请先登陆", "请先登陆");
                mSubscriberOnNextListener.onFail(new NetError("请先登陆", NetError.ErrorType.AuthError));
            }
        }
    }
}
