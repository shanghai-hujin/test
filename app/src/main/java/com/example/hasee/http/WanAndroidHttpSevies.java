package com.example.hasee.http;

import com.example.hasee.bean.LoginResponse;

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

public interface WanAndroidHttpSevies {

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


    @GET
    Observable<Object> getWeather(@Url String url,
                                         @Query("areaid") String areaid,
                                         @Query("showapi_appid") String showapi_appid,
                                         @Query("showapi_sign") String showapi_sign);


}
