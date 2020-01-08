package com.example.hasee.common.net;

import android.content.Context;
import android.text.TextUtils;

import com.example.hasee.common.net.cache.CacheInterceptor;
import com.example.hasee.common.net.cache.ICache;
import com.example.hasee.common.net.interceptor.CallInterceptor;
import com.example.hasee.common.utils.FileUtil;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.readystatesoftware.chuck.ChuckInterceptor;

import java.io.File;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpHelper implements IDataHelper {
    private static NetConfig netConfig = new NetConfig();

    public Context context;

    /**
     * 可以用一个map替代
     */
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
        if (iCache.contains(serviceClass.getName())) {
            return (S) iCache.get(serviceClass.getName());
        } else {
            //不等，需要重新创建
            Object obj = createApi(serviceClass);
            iCache.put(serviceClass.getName(), obj);
            return (S) obj;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> S createApi(Class<S> serviceClass) {
        return createApi(serviceClass, getOkHttpClent());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> S createApi(Class<S> serviceClass, OkHttpClient client) {
        String baseUrl = netConfig.baseURL;
        if (netConfig.isUseMultiBaseURL) {
            try {
                //找serviceClass里面是否有baseurl变量  一定是共有的
                Field baseUrlField = serviceClass.getField("baseUrl");
                baseUrl = (String) baseUrlField.get(serviceClass);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                baseUrl = netConfig.baseURL;
            }
            if (TextUtils.isEmpty(baseUrl)) {
                throw new RuntimeException("hujin de 项目 : baseurl is 空的 or empt,未设置netconfig");
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
     * 其他请使用netConfig 的添加
     *
     * @param baseUrl
     * @return
     */
    private Retrofit getRetrofit(String baseUrl) {
        if (okHttpClient == null) {
            okHttpClient = getOkHttpClent();
        }
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create());
        if (netConfig.factories != null) {
            //其余格式方式 走这里
            for (int i = 0; i < netConfig.factories.length; i++) {
                retrofitBuilder.addConverterFactory(netConfig.factories[i]);
            }
        }
        if (netConfig.isUseRx) {
            retrofitBuilder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        }
        retrofit = retrofitBuilder.build();
        return retrofit;

    }

    @SuppressWarnings("unchecked")
    @Override
    public <S> S getApi(Class<S> serviceClass, OkHttpClient client) {
        if (iCache.contains(serviceClass.getName())) {
            return (S) iCache.get(serviceClass.getName());
        } else {
            Object obj = createApi(serviceClass, client);
            iCache.put(serviceClass.getName(), obj);
            return (S) obj;
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public OkHttpClient getClent() {
        if (okHttpClient == null) {
            return getOkHttpClent();
        }
        return okHttpClient;
    }

    private OkHttpClient getOkHttpClent() {
        File cacheFile = new File(FileUtil.getCacheDirectory(context), "Cache");//缓存路径
        cacheFile = FileUtil.makeDirs(cacheFile);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        CacheInterceptor cacheInterceptor = new CacheInterceptor(context);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(netConfig.connectTimeoutMills, TimeUnit.SECONDS)
                .writeTimeout(netConfig.whiteTimeoutMills, TimeUnit.SECONDS)
                .readTimeout(netConfig.readTimeoutMills, TimeUnit.SECONDS)
                .retryOnConnectionFailure(netConfig.isRetryOnConnectionFailure)
                .cache(cache)
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor);
        switch (netConfig.cookType) {
            case 0:
                //无操作
                break;
            case 1:
                //本地cook
               // builder.cookieJar(new CookiesManager(context));
                break;
            case 2:
                //默认这套
                //开源方案
                ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));//对cooke自动管理管理
                builder.cookieJar(cookieJar);
            default:
                break;
        }


        if (netConfig.isHasLog) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }
        if (netConfig.isHasChuck) {
            builder.addInterceptor(new ChuckInterceptor(context));
        }
        //设置https相关，可能会有用,设置了一个回掉，用到去实现，拿到builder
        if (netConfig.httpsCall != null) {
            netConfig.httpsCall.configHttps(builder);
        }
        //添加一个请求的前后监听
        if (netConfig.requestCall != null) {
            builder.addInterceptor(new CallInterceptor(netConfig.requestCall));
        }
        //其余的拦截器
        if (netConfig.interceptors != null) {
            for (int i = 0; i < netConfig.interceptors.length; i++) {
                builder.addInterceptor(netConfig.interceptors[i]);
            }
        }
        okHttpClient = builder.build();
        return okHttpClient;
    }
}
