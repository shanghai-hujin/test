package com.example.hasee.http;

import com.example.hasee.bean.GanRandomBean;
import com.example.hasee.bean.HistoryTodayBean;
import com.example.hasee.bean.LoginResponse;
import com.example.hasee.bean.MeiRiYiWenBean;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.bean.WeatherBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 13:40
 */

public interface OtherHttpSevies {

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


    /**
     * 获取天气
     * @param url
     * @param areaid
     * @param location
     * @return
     */
    @GET
    Observable<WeatherBean> getWeather(@Url String url,
                                       @Query("key") String areaid,
                                       @Query("location") String location);


    /**
     * 获取正在上映电影
     *
     * @param url
     * @param start 开始
     * @param count 数量
     * @param city  城市
     * @return
     */
    @GET
    Observable<MovieDataBean> getMovieInTheatersData(@Url String url,
                                                     @Query("start") int start,
                                                     @Query("count") int count,
                                                     @Query("city") String city);

    /**
     * 获取即将上映电影
     *
     * @param url
     * @param start 开始
     * @param count 数量
     * @return
     */
    @GET
    Observable<MovieDataBean> getMovieComingSoon(@Url String url,
                                               @Query("start") int start,
                                               @Query("count") int count);


    /**
     * 获取250电影
     *
     * @param url
     * @param start 开始
     * @param count 数量
     * @return
     */
    @GET
    Observable<MovieDataBean> getMovieTop250(@Url String url,
                                           @Query("start") int start,
                                           @Query("count") int count);


    /**
     * 获取口碑
     *
     * @param url
     * @return
     */
    @GET
    Observable<MovieDataBean> getMovieWeekly(@Url String url);


    /**
     * 获取tag
     *
     * @param url
     * @param start 开始
     * @param count 数量
     * @param q 查询
     * @param tag 标签
     * @return
     */
    @GET
    Observable<MovieDataBean> getMovieSearch(@Url String url,
                                           @Query("start") int start,
                                           @Query("count") int count,
                                           @Query("q") String q,
                                           @Query("tag") String tag);

    /**
     * 获取历史上的今天
     * @param url
     * @return
     */
    @GET
    Observable<HistoryTodayBean> getHistoryOfToday(@Url String url);

    /**
     * 获取Nasa故事
     * @param url
     * @return
     */
    @GET
    Observable<MeiRiYiWenBean> getNASAOfToday(@Url String url,
                                              @Query("AppKey") String AppKey);

    /**
     * 获取干货随机数据
     * @param type 类型
     * @param num  数量
     * @return
     */
    @GET("http://gank.io/api/random/data/{type}/{num}")
    Observable<GanRandomBean> getGanRandom(@Path("type") String type,
                                           @Path("num") int num);
}
