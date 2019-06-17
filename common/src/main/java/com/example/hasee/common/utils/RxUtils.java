package com.example.hasee.common.utils;

import android.util.Log;

import com.example.hasee.common.net.RetryWhenHandler;
import com.example.hasee.common.net.subscriber.IResponse;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by chao.qu at 2017/10/20
 * @author quchao
 */

public class RxUtils {

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return FlowableTransformer
     */
    public static <T extends IResponse> FlowableTransformer<T, T> rxSchedulerHelperNoRetry() {
        return flowable -> flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T extends IResponse> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return rxSchedulerHelper(3,3);
    }


    public static <T extends IResponse> FlowableTransformer<T, T> rxSchedulerHelper(final int count, final long delay) {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .flatMap(new Function<T, Flowable<T>>() {
                            @Override
                            public Flowable<T> apply(T t) throws Exception {

                                //  根据实际情况去处理
                                if(t.checkReLogin()){
                                    Log.d("RxUtils","请检查登陆状态");
                                }
                                return Flowable.just(t);
                            }
                        })
                        .retryWhen(new RetryWhenHandler(count, delay))
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 得到 Observable
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    public static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }
}
