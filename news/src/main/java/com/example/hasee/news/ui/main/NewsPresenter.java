package com.example.hasee.news.ui.main;

import android.util.Log;

import com.example.hasee.common.base.mvp.BasePresenter;
import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;
import com.example.hasee.news.net.NewsSubscriber;
import com.example.hasee.news.net.NewsSubscriberListener;

import java.util.List;

import javax.inject.Inject;

public class NewsPresenter extends BasePresenter<INewsContract.IView> implements INewsContract.IPresenter {

    private NewsService newsService;


    @Inject
    public NewsPresenter(NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    public void getWanHomeBanner() {
        newsService.getWanHomeBanner()
                .compose(getV().<WanResponseWapper<List<WanHomeBannerResponse>>>bindLifecycle())
                .subscribe(new NewsSubscriber<WanResponseWapper<List<WanHomeBannerResponse>>>(
                        new NewsSubscriberListener<List<WanHomeBannerResponse>>() {
                            @Override
                            public void onSuccess(List<WanHomeBannerResponse> response) {
                                int size = response.size();
                                Log.d("NewsPresenter==","size=="+size);
                                getV().showBannerData(response);
                            }
                        }));
    }
}
