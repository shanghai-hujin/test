package com.example.hasee.ui.news;

import com.example.hasee.bean.NewsArticleBean;
import com.example.hasee.http.NewsHttpApi;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.utils.RxUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/30 14:12
 */

public class ReadContentsPresenter extends BasePresenter<ReadContentsContract.ReadContentsView>
        implements ReadContentsContract.ReadContentsPresenter {



    @Override
    public void getData(String aid) {

        NewsHttpApi.getInstace().getNewsArticle(aid)
                .compose(RxUtils.<NewsArticleBean>rxSchedulerHelper())
                .compose(mView.<NewsArticleBean>bindToLife())
                .subscribe(new Observer<NewsArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsArticleBean newsArticleBean) {
                        mView.loadWebData(newsArticleBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
