package com.example.hasee.common.net.subscriber;

import com.example.hasee.common.net.NetError;

import io.reactivex.subscribers.ResourceSubscriber;

/**
 * 各自的dialog交给自己的模块处理（dialog 作用为同步，可封装在外面）
 *
 * @param <T>
 */
public class BaseSubscriber<T extends IResponse> extends ResourceSubscriber<T> {

    protected SubscriberListener mSubscriberOnNextListener;

    public BaseSubscriber(SubscriberListener mSubscriberOnNextListener) {
        this.mSubscriberOnNextListener = mSubscriberOnNextListener;
    }

    /**
     * 订阅开始时调用
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onStart();
        }


    }

    /**
     * 数据返回
     * @param t
     */
    @Override
    public void onNext(T t) {
        if (mSubscriberOnNextListener != null) {
            if (t!=null) {
                mSubscriberOnNextListener.onSuccess(t);
            }
            else {
                mSubscriberOnNextListener.onFail(new NetError("数据为空", NetError.ErrorType.NoDataError));
            }
        }
    }

    /**
     * 对错误统一处理
     *
     * @param t
     */
    @Override
    public void onError(Throwable t) {
        if (mSubscriberOnNextListener != null) {
            mSubscriberOnNextListener.onError(t);
        }
        onComplete();
    }

    @Override
    public void onComplete() {
        if (mSubscriberOnNextListener != null)
            mSubscriberOnNextListener.onCompleted();
        if (!this.isDisposed()) {
            this.dispose();
        }
    }
}
