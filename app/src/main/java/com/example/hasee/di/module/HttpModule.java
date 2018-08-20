package com.example.hasee.di.module;

import com.example.hasee.http.Common;
import com.example.hasee.http.OtherHttpApi;
import com.example.hasee.http.OtherHttpSevies;
import com.example.hasee.http.cookies.CookiesManager;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Demo ${CLASS}
 *
 * @author hj
 * @date 2018/8/20 09:45
 */
@Module
public class HttpModule {
    public final static int CONNECT_TIMEOUT = 10;
    public final static int READ_TIMEOUT = 10;
    public final static int WRITE_TIMEOUT = 10;

    @Provides
    OkHttpClient.Builder providesOkHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return  new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cookieJar(new CookiesManager())
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                ;
    }

    @Provides
    OtherHttpApi providesOtherHttpApi(OkHttpClient.Builder builder){
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(Common.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                ;
        return OtherHttpApi.getInstance(
                retrofitBuilder.build().create(OtherHttpSevies.class));

    }
}
