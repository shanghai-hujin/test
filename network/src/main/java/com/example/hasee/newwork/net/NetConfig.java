package com.example.hasee.newwork.net;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class NetConfig {

    //拦截器
    public Interceptor[] interceptors;
    //retrofit 解析适配工厂
    public Converter.Factory[] factories ;

    public CookieJar cookieJar;

    public RequestCall requestCall;

    public long connectTimeoutMills = 15;
    public long readTimeoutMills = 15;
    public long whiteTimeoutMills = 15;

    //log日志
    public boolean isHasLog = true;
    //chuck显示
    public boolean isHasChuck = true;
    public boolean isUseRx = true;
    //失败重连
    public boolean isRetryOnConnectionFailure = true;
    public String baseURL = "";
    //是否多个baseurl
    public boolean isUseMultiBaseURL = true;


    public Interceptor[] getInterceptors() {
        return interceptors;
    }

    public NetConfig setInterceptors(Interceptor[] interceptors) {
        this.interceptors = interceptors;
        return this;
    }

    public Converter.Factory[] getFactories() {
        return factories;
    }

    public NetConfig setFactories(Converter.Factory[] factories) {
        this.factories = factories;
        return this;
    }

    public CookieJar getCookieJar() {
        return cookieJar;
    }

    public NetConfig setCookieJar(CookieJar cookieJar) {
        this.cookieJar = cookieJar;
        return this;
    }

    public RequestCall getRequestCall() {
        return requestCall;
    }

    public NetConfig setRequestCall(RequestCall requestCall) {
        this.requestCall = requestCall;
        return this;
    }

    public long getConnectTimeoutMills() {
        return connectTimeoutMills;
    }

    public NetConfig setConnectTimeoutMills(long connectTimeoutMills) {
        this.connectTimeoutMills = connectTimeoutMills;
        return this;
    }

    public long getReadTimeoutMills() {
        return readTimeoutMills;
    }

    public NetConfig setReadTimeoutMills(long readTimeoutMills) {
        this.readTimeoutMills = readTimeoutMills;
        return this;
    }

    public boolean isHasLog() {
        return isHasLog;
    }

    public NetConfig setHasLog(boolean hasLog) {
        isHasLog = hasLog;
        return this;
    }

    public boolean isUseRx() {
        return isUseRx;
    }

    public NetConfig setUseRx(boolean useRx) {
        isUseRx = useRx;
        return this;
    }

    public String getBaseURL() {
        return baseURL;
    }

    public NetConfig setBaseURL(String baseURL) {
        this.baseURL = baseURL;
        return this;
    }

    public boolean isUseMultiBaseURL() {
        return isUseMultiBaseURL;
    }

    public NetConfig setUseMultiBaseURL(boolean useMultiBaseURL) {
        isUseMultiBaseURL = useMultiBaseURL;
        return this;
    }

    public boolean isHasChuck() {
        return isHasChuck;
    }

    public NetConfig setHasChuck(boolean hasChuck) {
        isHasChuck = hasChuck;
        return this;
    }

    public long getWhiteTimeoutMills() {
        return whiteTimeoutMills;
    }

    public NetConfig setWhiteTimeoutMills(long whiteTimeoutMills) {
        this.whiteTimeoutMills = whiteTimeoutMills;
        return this;
    }

    public boolean isRetryOnConnectionFailure() {
        return isRetryOnConnectionFailure;
    }

    public NetConfig setRetryOnConnectionFailure(boolean retryOnConnectionFailure) {
        isRetryOnConnectionFailure = retryOnConnectionFailure;
        return this;
    }

    public interface RequestCall {

        Request onBeforeRequest(Request request, Interceptor.Chain chain);

        Response onAfterRequest(Response response, ResponseBody result, Interceptor.Chain chain);
    }



}
