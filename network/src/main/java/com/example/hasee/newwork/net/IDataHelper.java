package com.example.hasee.newwork.net;

import okhttp3.OkHttpClient;

/**
 * 可以扩展
 */
public interface IDataHelper {

    <S> S getApi(Class<S> serviceClass);

    <S> S createApi(Class<S> serviceClass);

    <S> S getApi(Class<S> serviceClass, OkHttpClient client);

    <S> S createApi(Class<S> serviceClass, OkHttpClient client);

    OkHttpClient getClent();

    public interface HttpsCall {

        void configHttps(OkHttpClient.Builder builder);

    }
}
