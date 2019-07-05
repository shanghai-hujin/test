package com.example.hasee.common.net.subscriber;

import com.example.hasee.common.net.NetError;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

public abstract class BaseSubscriberListener<T> extends SubscriberListener<T> {

    @Override
    public void onFail(NetError error) {

    }

    @Override
    public void onError(Throwable e) {
        NetError error = null;
        if (e != null) {
            if (!(e instanceof NetError)) {
                if (e instanceof UnknownHostException) {
                    error = new NetError(e, NetError.ErrorType.NoConnectError);
                } else if (e instanceof JSONException
                        || e instanceof JsonParseException
                        || e instanceof JsonSyntaxException) {
                    error = new NetError(e, NetError.ErrorType.APParseErrorPS);
                } else if (e instanceof SocketException
                        || e instanceof SocketTimeoutException) {
                    error = new NetError(e, NetError.ErrorType.SocketError);
                } else if (e instanceof HttpException) {
                    HttpException httpException = (HttpException) e;
                    if (isCheckReLogin(httpException)) {//去认证
                        checkReLogin("401", "checkout");
                    }
                    error = new NetError(e, NetError.ErrorType.NetError);
                } else {
                    error = new NetError(e, NetError.ErrorType.NoTypeError);
                }
            } else {
                error = (NetError) e;
            }
            onFail(error);
        }
    }

    @Override
    public void checkReLogin(String errorCode, String errorMsg) {

    }

    @Override
    public boolean isCheckReLogin(HttpException httpException) {
        return false;
    }
}
