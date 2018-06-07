package com.example.hasee.http;

import com.example.hasee.bean.LoginResponse;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.bean.WeatherBean;
import com.example.hasee.http.cookies.CookiesManager;

import java.io.ObjectStreamException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 13:33
 */

public class OtherHttpApi {

    public final static int CONNECT_TIMEOUT = 10;
    public final static int READ_TIMEOUT = 10;
    public final static int WRITE_TIMEOUT = 10;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private final OtherHttpSevies httpSevies;



    //私有构造函数
    private OtherHttpApi() {

        // 防止反射获取多个对象的漏洞
        if (null != HelperSinger.sSingletonTest) {
            throw new RuntimeException("单例被反射");
        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cookieJar(new CookiesManager())
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

        httpSevies = retrofit.create(OtherHttpSevies.class);


    }

    //静态内部类,
    private static class HelperSinger{
        private static OtherHttpApi sSingletonTest = new OtherHttpApi();
    }


    public static OtherHttpApi getInstace(){
        return HelperSinger.sSingletonTest;
    }

    //防止反序列化产生多个对象
    private Object readResolve() throws ObjectStreamException {
        return OtherHttpApi.getInstace();
    }




    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public Observable<LoginResponse> getLoginData(String username, String password) {
        return httpSevies.getLoginData(username, password);
    }


    /**
     * 获取天气
     * @param key
     * @param location
     * @return
     */
    public Observable<WeatherBean> getWeather(String key , String location) {
        return httpSevies.getWeather(Common.API_Weather, key, location);
    }


    /**
     * 获取 正在上映
     * @param start
     * @param count
     * @param city
     * @return
     */
    public Observable<MovieDataBean> getMovieInTheatersData(int start, int count, String city){
        return httpSevies.getMovieInTheatersData(Common.IN_THEATERS, start, count, city);
    }

    /**
     * 获取 banner
     * @return
     */
    public Observable<MovieDataBean> getMovieWeekly(){
        return httpSevies.getMovieWeekly(Common.WEEKLY);
    }
}
