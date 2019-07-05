package com.example.hasee.news.net;

import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface NewsApi {


    /**
     * 获取banner首页数据
     * @return
     */
    @GET("banner/json")
    Flowable<WanResponseWapper<List<WanHomeBannerResponse>>> getWanHomeBanner();

}
