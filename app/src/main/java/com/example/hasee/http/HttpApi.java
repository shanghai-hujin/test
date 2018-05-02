package com.example.hasee.http;

import com.example.hasee.bean.LoginResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.ObjectStreamException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 13:33
 */

public class HttpApi {

    public final static int CONNECT_TIMEOUT = 10;
    public final static int READ_TIMEOUT = 10;
    public final static int WRITE_TIMEOUT = 10;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private final HttpSevies httpSevies;

    //私有构造函数
    private HttpApi() {

        // 防止反射获取多个对象的漏洞
        if (null != HelperSinger.sSingletonTest) {
            throw new RuntimeException("单例被反射");
        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cookieJar(null)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Common.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        httpSevies = retrofit.create(HttpSevies.class);


    }

    //静态内部类,
    private static class HelperSinger{
        private static HttpApi sSingletonTest = new HttpApi();
    }


    public static HttpApi getInstace(){
        return HelperSinger.sSingletonTest;
    }

    //防止反序列化产生多个对象
    private Object readResolve() throws ObjectStreamException {
        return HttpApi.getInstace();
    }


    /**
     * 返回被观察者对象
     * @param username
     * @param password
     * @return
     */
    public Observable<LoginResponse> getLoginData(String username, String password) {
        return httpSevies.getLoginData(username, password);
    }

}
