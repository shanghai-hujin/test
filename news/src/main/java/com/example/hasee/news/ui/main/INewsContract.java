package com.example.hasee.news.ui.main;

import com.example.hasee.common.base.mvp.IBaseModle;
import com.example.hasee.common.base.mvp.IBasePresenter;
import com.example.hasee.common.base.mvp.IBaseView;
import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.net.bean.response.WanWenZhangResponse;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;

import java.util.List;

import io.reactivex.Flowable;

public interface INewsContract {

    interface IView extends IBaseView{
        void showBannerData(List<WanHomeBannerResponse> response);

        void showNewsListDate(WanWenZhangResponse wanWenZhangResponse);
    }

    interface IPresenter extends IBasePresenter<IView>{

        void getWanHomeBanner();

        void getWanHomeWenZhang(String curpage);

    }

    interface IModle extends IBaseModle{
        Flowable<WanResponseWapper<List<WanHomeBannerResponse>>> getWanHomeBanner();

        Flowable<WanResponseWapper<WanWenZhangResponse>> getWanHomeWenZhang(String curpage);


    }

}
