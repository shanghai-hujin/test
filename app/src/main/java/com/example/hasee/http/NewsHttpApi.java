//package com.example.hasee.http;
//
//import android.support.annotation.StringDef;
//import android.util.Log;
//
//import com.example.hasee.bean.NewsArticleBean;
//import com.example.hasee.bean.NewsDetail;
//import com.example.hasee.ui.MyApplication;
//import com.example.hasee.utils.NetUtil;
//
//import java.io.IOException;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.util.List;
//
//import io.reactivex.Observable;
//import io.reactivex.ObservableSource;
//import io.reactivex.functions.Function;
//import okhttp3.CacheControl;
//import okhttp3.HttpUrl;
//import okhttp3.Interceptor;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//import retrofit2.Retrofit;
//
///**
// * Demo ${CLASS}
// *
// * @author TT
// * @date 2018/5/10 11:27
// */
//
//public class NewsHttpApi {
//
//    //设缓存有效期为1天
//    static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
//    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
//    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
//
//    /**
//     * 云端响应头拦截器，用来配置缓存策略
//     * Dangerous interceptor that rewrites the server's cache-control header.
//     */
//    public static final Interceptor sRewriteCacheControlInterceptor = new Interceptor() {
//
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            if (!NetUtil.isNetworkAvailable(MyApplication.getContext())) {
//                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
//                Log.e("NewsApi", "no network");
//            }
//            Response originalResponse = chain.proceed(request);
//
//            if (NetUtil.isNetworkAvailable(MyApplication.getContext())) {
//                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
//                String cacheControl = request.cacheControl().toString();
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", cacheControl)
//                        .removeHeader("Pragma")
//                        .build();
//            } else {
//                return originalResponse.newBuilder()
//                        .header("Cache-Control", "public, " + CACHE_CONTROL_CACHE)
//                        .removeHeader("Pragma")
//                        .build();
//            }
//        }
//    };
//
//    public static final Interceptor sQueryParameterInterceptor = new Interceptor() {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request commonRequest = chain.request();
//            Request request;
//            HttpUrl modifiedUrl = commonRequest.url().newBuilder()
//                    .addQueryParameter("uid", Common.uid )
//                    .addQueryParameter("devid", Common.uid)
//                    .addQueryParameter("proid", "ifengnews")
//                    .addQueryParameter("vt", "5")
//                    .addQueryParameter("publishid", "6103")
//                    .addQueryParameter("screen", "1080x1920")
//                    .addQueryParameter("os", "androidphone")
//                    .addQueryParameter("df", "android_22")
//                    .addQueryParameter("nw", "wifi")
//                    .build();
//            request = commonRequest.newBuilder().url(modifiedUrl).build();
//
//            return chain.proceed(request);
//        }
//    };
//    private  OkHttpClient okHttpClient;
//    private  Retrofit retrofit;
//    private  NewsHttpSevies httpSevies;
//    public static NewsHttpApi sInstance;
//
//    public NewsHttpApi(NewsHttpSevies httpSevies) {
//        this.httpSevies = httpSevies;
//    }
//
//
//    public static NewsHttpApi getInstance(NewsHttpSevies newsHttpSevies){
//        if(sInstance == null){
//            sInstance = new NewsHttpApi(newsHttpSevies);
//        }
//        return sInstance;
//    }
//
//    public static final String ACTION_DEFAULT = "default";
//    public static final String ACTION_DOWN = "down";
//    public static final String ACTION_UP = "up";
//
//    @StringDef({ACTION_DEFAULT,ACTION_DOWN,ACTION_UP})
//    @Retention(RetentionPolicy.SOURCE)
//    public @interface Actions{
//
//    }
//
//    /**
//     * 获取新闻
//     * @param id 新闻类型id
//     * @param action 用户动作：默认、下拉、上拉
//     * @param pullNum 推送数量
//     * @return 通过flatmap转换
//     */
//    public Observable<NewsDetail> getNewsDetail(String id, @Actions String action, int pullNum){
//        return httpSevies.getNewsDetail( id, action, pullNum)
//                .flatMap(new Function<List<NewsDetail>, ObservableSource<NewsDetail>>() {
//                    @Override
//                    public ObservableSource<NewsDetail> apply(List<NewsDetail> newsDetails) throws Exception {
//                        return Observable.fromIterable(newsDetails);
//                    }
//                });
//    }
//
//    /**
//     * 获取新闻详情文章
//     * @param aid  文章id
//     * @return
//     */
//    public Observable<NewsArticleBean> getNewsArticle(String aid){
//        if(aid.startsWith("sub")){
//            return httpSevies.getNewsArticleWithSub(aid);
//        }else {
//            return httpSevies.getNewsArticleWithCmpp(Common.GetNewsArticleCmppApi+ Common.GetNewsArticleDocCmppApi, aid);
//        }
//    }
//
//}
