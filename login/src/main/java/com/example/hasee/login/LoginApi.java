package com.example.hasee.login;

import com.example.hasee.common.net.bean.request.LoginRequest;
import com.example.hasee.common.net.bean.response.LoginResponce;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {

    public String baseurl  = "https://www.wanandroid.com/";

    /**
     * 登陆
     * http://www.wanandroid.com/user/login
     * 服务端不支持body
     */
    @POST("user/login")
    Flowable<WanResponseWapper<LoginResponce>> getLoginData(@Body LoginRequest loginRequest);

    @POST("user/login")
    @FormUrlEncoded
    Flowable<WanResponseWapper<LoginResponce>> getLoginData(@Field("username") String username,
                                                              @Field("password") String password);
}
