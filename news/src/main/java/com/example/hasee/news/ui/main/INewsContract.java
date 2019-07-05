package com.example.hasee.news.ui.main;

import com.example.hasee.common.base.mvp.IBaseModle;
import com.example.hasee.common.base.mvp.IBasePresenter;
import com.example.hasee.common.base.mvp.IBaseView;
import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;

import java.util.List;

import io.reactivex.Flowable;

public interface INewsContract {

    interface IView extends IBaseView{
        void showBannerData(List<WanHomeBannerResponse> response);
    }

    interface IPresenter extends IBasePresenter<IView>{

        void getWanHomeBanner();

    }

    interface IModle extends IBaseModle{
        Flowable<WanResponseWapper<List<WanHomeBannerResponse>>> getWanHomeBanner();
    }

}
