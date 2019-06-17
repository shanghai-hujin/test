package com.example.hasee.login;

import com.example.hasee.common.net.subscriber.BaseSubscriberListener;

import retrofit2.HttpException;

/**
 * 可能每个模块 对登陆的处理可能不一样
 * @param <T>
 */
public abstract class LoginSubscriberListener<T> extends BaseSubscriberListener<T> {
    //对应HTTP的状态码
    private static final int ERROR = 400;
    private static final int UNAUTHORIZED = 401;//没有权限
    private static final int FORBIDDEN = 403;//没有权限
    private static final int NOT_FOUND = 404;//
    private static final int INTERNAL_SERVER_ERROR = 500;//服务器错误
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;


    @Override
    public void checkReLogin(String errorCode, String errorMsg) {
//        AccountManager.INSTANCE.logout();
//        RxBus.getDefault().post(new Events<String>(GO_LOGIN, AppContext.get().getString(R.string.GO_LOGIN)));
    }

    @Override
    public boolean isCheckReLogin(HttpException httpException) {
        return httpException.code() == UNAUTHORIZED;
    }

}
