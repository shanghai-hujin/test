package com.example.hasee.login;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginApi {
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
}
