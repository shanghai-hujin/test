package com.example.hasee.newwork.net;

import android.content.Context;
import android.text.TextUtils;

import com.example.hasee.newwork.cache.ICache;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelper implements IDataHelper {
    private static NetConfig netConfig = new NetConfig();

    public Context context;

    private ICache iCache;

    private static OkHttpClient okHttpClient = null;

    private static Retrofit retrofit = null;

    public HttpHelper(Context context) {
        this.context = context;
    }

    public HttpHelper(Context context, ICache iCache) {
        this.context = context;
        this.iCache = iCache;
    }


    public void initConfig(NetConfig netConfig) {
        this.netConfig = netConfig;
    }


    @SuppressWarnings("unchecked")
    @Override
    public <S> S getApi(Class<S> serviceClass) {
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> S createApi(Class<S> serviceClass) {
        return createApi(serviceClass, getOkHttpClent());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> S createApi(Class<S> serviceClass , OkHttpClient client) {
        String baseUrl = netConfig.baseURL;
        if (netConfig.isUseMultiBaseURL) {
            try {
                //找serviceClass里面是否有baseurl变量  一定是共有的
                Field baseUrlField = serviceClass.getField("baseUrl");
                baseUrl = (String) baseUrlField.get(serviceClass);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                baseUrl = netConfig.baseURL;
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                baseUrl = netConfig.baseURL;
            }
            if (TextUtils.isEmpty(baseUrl)) {
                throw new RuntimeException("hujin de xiangmu : baseurl is null or empty");
            }
        }
        //需要比较  上次的retrofit 和这次的retrofit 的baseurl 是否一致
        if (retrofit != null && retrofit.baseUrl().host().equals(baseUrl)) {
            return retrofit.create(serviceClass);
        } else {
            return getRetrofit(baseUrl).create(serviceClass);
        }

    }

    /**
     * 默认gson解析
     *  其他请使用netConfig 的添加
     * @param baseUrl
     * @return
     */
    private Retrofit getRetrofit(String baseUrl) {
        if(okHttpClient == null){
            okHttpClient= getOkHttpClent();
        }
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
        if(netConfig.factories != null){
            //其余格式方式 走这里
            for (int i = 0; i < netConfig.factories.length; i++) {
                retrofitBuilder.addConverterFactory(netConfig.factories[i]);
            }
        }
        if(netConfig.isUseRx){
            retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        return retrofitBuilder.build();

    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> S getApi(Class<S> serviceClass, OkHttpClient client) {
        return null;
    }


    @SuppressWarnings("unchecked")
    @Override
    public OkHttpClient getClent() {
        if(okHttpClient == null){
            return getOkHttpClent();
        }
        return okHttpClient;
    }

    private OkHttpClient getOkHttpClent() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder;
        builder.connectTimeout(netConfig.connectTimeoutMills, TimeUnit.SECONDS)
                .writeTimeout(netConfig.whiteTimeoutMills, TimeUnit.SECONDS)
                .readTimeout(netConfig.readTimeoutMills, TimeUnit.SECONDS)
                .retryOnConnectionFailure(netConfig.isRetryOnConnectionFailure);


        return null;
    }
}
