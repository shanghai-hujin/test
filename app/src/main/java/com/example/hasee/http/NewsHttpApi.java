package com.example.hasee.http;

import android.support.annotation.StringDef;
import android.util.Log;

import com.example.hasee.bean.NewsDetail;
import com.example.hasee.ui.MyApplication;
import com.example.hasee.utils.NetUtil;

import java.io.File;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/10 11:27
 */

public class NewsHttpApi {

    //设缓存有效期为1天
    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    public static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetUtil.isNetworkAvailable(MyApplication.getContext())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                Log.e("NewsApi", "no network");
            }
            Response originalResponse = chain.proceed(request);

            if (NetUtil.isNetworkAvailable(MyApplication.getContext())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    public static final Interceptor sQueryParameterInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request commonRequest = chain.request();
            Request request;
            HttpUrl modifiedUrl = commonRequest.url().newBuilder()
                    .addQueryParameter("uid", Common.uid )
                    .addQueryParameter("devid", Common.uid)
                    .addQueryParameter("proid", "ifengnews")
                    .addQueryParameter("vt", "5")
                    .addQueryParameter("publishid", "6103")
                    .addQueryParameter("screen", "1080x1920")
                    .addQueryParameter("os", "androidphone")
                    .addQueryParameter("df", "android_22")
                    .addQueryParameter("nw", "wifi")
                    .build();
            request = commonRequest.newBuilder().url(modifiedUrl).build();

            return chain.proceed(request);
        }
    };
    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;
    private final NewsHttpSevies httpSevies;

    //私有构造函数
    private NewsHttpApi() {

        // 防止反射获取多个对象的漏洞
        if (null != NewsHttpApi.HelperSinger.sSingletonTest) {
            throw new RuntimeException("单例被反射");
        }

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 指定缓存路径,缓存大小100Mb
        Cache cache = new Cache(new File(MyApplication.getContext().getCacheDir(), "HttpCache"),
                1024 * 1024 * 100);

        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(sRewriteCacheControlInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(sQueryParameterInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Common.GetNewsArticleCmppApi)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        httpSevies = retrofit.create(NewsHttpSevies.class);


    }


    //静态内部类,
    private static class HelperSinger{
        private static NewsHttpApi sSingletonTest = new NewsHttpApi();
    }


    public static NewsHttpApi getInstace(){
        return NewsHttpApi.HelperSinger.sSingletonTest;
    }

    //防止反序列化产生多个对象
    private Object readResolve() throws ObjectStreamException {
        return NewsHttpApi.getInstace();
    }

    public static final String ACTION_DEFAULT = "default";
    public static final String ACTION_DOWN = "down";
    public static final String ACTION_UP = "up";

    @StringDef({ACTION_DEFAULT,ACTION_DOWN,ACTION_UP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Actions{

    }

    /**
     * 获取新闻
     * @param id 新闻类型id
     * @param action 用户动作：默认、下拉、上拉
     * @param pullNum 推送数量
     * @return 通过flatmap转换
     */
    public Observable<NewsDetail> getNewsDetail(String id, @Actions String action, int pullNum){
        return httpSevies.getNewsDetail( id, action, pullNum)
                .flatMap(new Function<List<NewsDetail>, ObservableSource<NewsDetail>>() {
                    @Override
                    public ObservableSource<NewsDetail> apply(List<NewsDetail> newsDetails) throws Exception {
                        return Observable.fromIterable(newsDetails);
                    }
                });
    }

}
