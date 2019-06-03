package com.example.hasee.newwork.net;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Converter;

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

    public interface RequestCall {

        Request onBeforeRequest(Request request, Interceptor.Chain chain);

        Response onAfterRequest(Response response, ResponseBody result, Interceptor.Chain chain);
    }

    public class NetConfig {


        //拦截器
        public Interceptor[] interceptors;
        //retrofit 解析适配工厂
        public Converter.Factory[] factories ;

        public CookieJar cookieJar;

        public IDataHelper.RequestCall requestCall;

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

        public IDataHelper.HttpsCall httpsCall;
        //cook 策略  0 无， 1，本地那套  2，PersistentCookieJar git上的
        public int cookType = 2;

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

        public IDataHelper.RequestCall getRequestCall() {
            return requestCall;
        }

        public NetConfig setRequestCall(IDataHelper.RequestCall requestCall) {
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

        public IDataHelper.HttpsCall getHttpsCall() {
            return httpsCall;
        }

        public NetConfig setHttpsCall(IDataHelper.HttpsCall httpsCall) {
            this.httpsCall = httpsCall;
            return this;
        }

        public int getCookType() {
            return cookType;
        }

        public NetConfig setCookType(int cookType) {
            this.cookType = cookType;
            return this;
        }
    }

}
