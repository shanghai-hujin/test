package com.example.hasee.common.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CallInterceptor implements Interceptor {
    private IDataHelper.RequestCall requestCall;
    public CallInterceptor(IDataHelper.RequestCall requestCall) {
        this.requestCall = requestCall;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //https://blog.csdn.net/zhangteng22/article/details/52233126  Response.string只能掉一次
        Request request = chain.request();
        if (requestCall != null) {
            if (requestCall.onBeforeRequest(request, chain) != null) {
                request = requestCall.onBeforeRequest(request,chain);
            }
        }
        Response response = chain.proceed(request);
        if (requestCall != null) {
            if (requestCall.onAfterRequest(response, response.body(), chain) != null) {
                response = requestCall.onAfterRequest(response, response.body(), chain);
            }
        }
        return response;
    }
}
