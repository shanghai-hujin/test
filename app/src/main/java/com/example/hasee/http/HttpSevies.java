package com.example.hasee.http;

import com.example.hasee.bean.LoginResponse;
import com.example.hasee.bean.NewsArticleBean;
import com.example.hasee.bean.NewsCmppVideoBean;
import com.example.hasee.bean.NewsDetail;
import com.example.hasee.bean.NewsImagesBean;
import com.example.hasee.bean.VideoChannelBean;
import com.example.hasee.bean.VideoDetailBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 13:40
 */

public interface HttpSevies {

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     *
     * @param username user name
     * @param password password
     * @return Observable<LoginResponse>
     */
    @POST("user/login")
    @FormUrlEncoded
    Observable<LoginResponse> getLoginData(@Field("username") String username, @Field("password") String password);


    //由于api的base地址不一样，以下为其他接口地址
    @GET
    Observable<List<NewsDetail>> getNewsDetail(@Url String url,
                                               @Query("id") String id,
                                               @Query("action") String action,
                                               @Query("pullNum") int pullNum
    );

    @GET("api_vampire_article_detail")
    Observable<NewsArticleBean> getNewsArticleWithSub(@Query("aid") String aid);

    @GET
    Observable<NewsArticleBean> getNewsArticleWithCmpp(@Url String url,
                                                       @Query("aid") String aid);

    @GET
    Observable<NewsImagesBean> getNewsImagesWithCmpp(@Url String url,
                                                     @Query("aid") String aid);

    @GET("NewRelativeVideoList")
    Observable<NewsCmppVideoBean> getNewsVideoWithCmpp(@Url String url,
                                                       @Query("guid") String guid);

    @GET("ifengvideoList")
    Observable<List<VideoChannelBean>> getVideoChannel(@Query("page") int page);

    @GET("ifengvideoList")
    Observable<List<VideoDetailBean>> getVideoDetail(@Query("page") int page,
                                                     @Query("listtype") String listtype,
                                                     @Query("typeid") String typeid);
}
