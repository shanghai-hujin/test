package com.example.hasee.http;

import android.support.annotation.StringDef;

import com.example.hasee.bean.LoginResponse;
import com.example.hasee.bean.NewsDetail;
import com.example.hasee.http.cookies.CookiesManager;

import java.io.ObjectStreamException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
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

public class HttpApi {

    public final static int CONNECT_TIMEOUT = 10;
    public final static int READ_TIMEOUT = 10;
    public final static int WRITE_TIMEOUT = 10;
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private final HttpSevies httpSevies;

    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";

    @StringDef({ACTION_DEFAULT,ACTION_DOWN,ACTION_UP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Actions{

    }

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

    /**
     * 获取新闻
     * @param id 新闻类型id
     * @param action 用户动作：默认、下拉、上拉
     * @param pullNum 推送数量
     * @return 通过flatmap转换
     */
    public Observable<NewsDetail> getNewsDetail(String id, @Actions String action, int pullNum){
        return httpSevies.getNewsDetail(Common.GetNewsArticleCmppApi+"ClientNews", id, action, pullNum)
                .flatMap(new Function<List<NewsDetail>, ObservableSource<NewsDetail>>() {
                    @Override
                    public ObservableSource<NewsDetail> apply(List<NewsDetail> newsDetails) throws Exception {
                        return Observable.fromIterable(newsDetails);
                    }
                });
    }

}
