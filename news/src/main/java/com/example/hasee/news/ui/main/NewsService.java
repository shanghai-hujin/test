package com.example.hasee.news.ui.main;

import com.example.hasee.common.net.HttpHelper;
import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;
import com.example.hasee.common.utils.RxUtils;
import com.example.hasee.news.net.NewsApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class NewsService implements INewsContract.IModle {

    private HttpHelper httpHelper;


    /**
     *说明:被inject标记了，会被注入到被 component标记的目标
     * 构造函数中的参数由 module来提供
     * 然后在 component标记的目标中使用  @Inject标记后，直接使用该对象
     * 可以多个一起
     *作者:hujin
     *添加时间:2018/8/20 11:07
     *修改人:hujin
     *修改时间:2018/8/20 11:07
     */
    @Inject
    public NewsService(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Flowable<WanResponseWapper<List<WanHomeBannerResponse>>> getWanHomeBanner() {

        return httpHelper.createApi(NewsApi.class).getWanHomeBanner()
                .compose(RxUtils.<WanResponseWapper<List<WanHomeBannerResponse>>>rxSchedulerHelperNoRetry());
    }
}
