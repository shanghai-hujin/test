package com.example.hasee.newwork.subscriber;

/**
 * 出参 需要实现的
 * @param <T>
 */
public interface IResponse<T> {

    boolean isSuccess();

    boolean isLogin();

    T getData();
}
