package com.example.hasee.ui.news;

import android.support.annotation.NonNull;

import com.example.hasee.bean.NewsDetail;
import com.example.hasee.bean.NewsUtils;
import com.example.hasee.http.NewsHttpApi;
import com.example.hasee.ui.base.BaseObserver;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.utils.RxUtils;
import com.orhanobut.logger.Logger;

import java.util.Iterator;
import java.util.List;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/8 10:01
 */

public class DetailPresenter extends BasePresenter<DetailContract.DetailVew> implements DetailContract.DetailPresenter{

    @Override
    public void getData(String newsid, String action, int pullNum) {
        NewsHttpApi.getInstace().getNewsDetail(newsid, action, pullNum)
                .compose(RxUtils.<NewsDetail>rxSchedulerHelper())
                .filter(new Predicate<NewsDetail>() {
                    @Override
                    public boolean test(@NonNull NewsDetail newsDetail) throws Exception {
                        if(NewsUtils.isBannerNews(newsDetail)){
                            mView.loadBannerData(newsDetail);
                        }
                        if(NewsUtils.isTopNews(newsDetail)){
                            mView.loadTopNewsData(newsDetail);
                        }
                        return NewsUtils.isListNews(newsDetail);
                    }
                })
                .map(new Function<NewsDetail, List<NewsDetail.ItemBean>>() {
                    @Override
                    public List<NewsDetail.ItemBean> apply(@NonNull NewsDetail newsDetail) throws Exception {
                        Iterator<NewsDetail.ItemBean> iterator = newsDetail.getItem().iterator();
                        while (iterator.hasNext()){
                            try {
                                NewsDetail.ItemBean itemBean = iterator.next();
                                if(itemBean.getType().equals(NewsUtils.TYPE_DOC)){
                                    if(itemBean.getStyle().getView() != null){
                                        if(itemBean.getStyle().getView().equals(NewsUtils.VIEW_TITLEIMG)){
                                            itemBean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                        }else {
                                            itemBean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                        }
                                    }
                                }else if (itemBean.getType().equals(NewsUtils.TYPE_ADVERT)) {
                                    if (itemBean.getStyle() != null) {
                                        if (itemBean.getStyle().getView().equals(NewsUtils.VIEW_TITLEIMG)) {
                                            itemBean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                        } else if (itemBean.getStyle().getView().equals(NewsUtils.VIEW_SLIDEIMG)) {
                                            itemBean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_SLIDEIMG;
                                        } else {
                                            itemBean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_LONGIMG;
                                        }
                                    } else {
                                        //bean.itemType = NewsDetail.ItemBean.TYPE_ADVERT_TITLEIMG;
                                        iterator.remove();
                                    }
                                } else if (itemBean.getType().equals(NewsUtils.TYPE_SLIDE)) {
                                    if (itemBean.getLink().getType().equals("doc")) {
                                        if (itemBean.getStyle().getView().equals(NewsUtils.VIEW_SLIDEIMG)) {
                                            itemBean.itemType = NewsDetail.ItemBean.TYPE_DOC_SLIDEIMG;
                                        } else {
                                            itemBean.itemType = NewsDetail.ItemBean.TYPE_DOC_TITLEIMG;
                                        }
                                    } else {
                                        itemBean.itemType = NewsDetail.ItemBean.TYPE_SLIDE;
                                    }
                                } else if (itemBean.getType().equals(NewsUtils.TYPE_PHVIDEO)) {
                                    itemBean.itemType = NewsDetail.ItemBean.TYPE_PHVIDEO;
                                } else {
                                    // 凤凰新闻 类型比较多，目前只处理能处理的类型
                                    iterator.remove();
                                }
                            } catch (Exception e) {
                                iterator.remove();
                                e.printStackTrace();
                            }
                        }
                        return newsDetail.getItem();
                    }
                })
                .compose(mView.<List<NewsDetail.ItemBean>>bindToLife())
                .subscribe(new BaseObserver<List<NewsDetail.ItemBean>>(mView) {
                    @Override
                    public void onNext(List<NewsDetail.ItemBean> itemBeans) {
                        if(!action.equals(NewsHttpApi.ACTION_UP)){
                            mView.loadData(itemBeans);
                        }else {
                            mView.loadMoreData(itemBeans);
                        }
                    }


                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Logger.e("TAG", "onFail: " + e.getMessage().toString());
                        if (!action.equals(NewsHttpApi.ACTION_UP)) {
                            mView.loadData(null);
                        } else {
                            mView.loadMoreData(null);
                        }
                    }
                });


    }
}
