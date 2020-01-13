package com.example.hasee.news.net;

import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.net.bean.response.WanWenZhangResponse;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsApi {


    /**
     * 获取banner首页数据
     * @return
     */
    @GET("banner/json")
    Flowable<WanResponseWapper<List<WanHomeBannerResponse>>> getWanHomeBanner();

    /**
     * 获取banner首页文章
     * @return
     */
    @GET("article/list/{curpage}/json")
    Flowable<WanResponseWapper<WanWenZhangResponse>> getWanHomeWenZhang(@Path("curpage") String curpage);

}
