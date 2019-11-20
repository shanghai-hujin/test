package com.example.hasee.common.net.subscriber;

/**
 * 出参 需要实现的
 * @param <T>
 */
public interface IResponse<T> {

    boolean isSuccess();

    boolean checkReLogin();

    String getErrorMsg();

    T getData();
}
