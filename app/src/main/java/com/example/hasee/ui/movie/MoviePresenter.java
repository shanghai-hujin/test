package com.example.hasee.ui.movie;

import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.http.OtherHttpApi;
import com.example.hasee.ui.base.BaseObserver;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.utils.RxUtils;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/5 18:54
 */

public class MoviePresenter extends BasePresenter<MovieContract.MovieView> implements MovieContract.MoviePresenter{

    @Override
    public void getMovieInTheatersData(int start, int count, String city) {
        OtherHttpApi.getInstace().getMovieInTheatersData(start, count, city)
                .compose(RxUtils.<MovieDataBean>rxSchedulerHelper())
                .compose(mView.<MovieDataBean>bindToLife())
                .subscribe(new BaseObserver<MovieDataBean>(mView) {
                    @Override
                    public void onNext(MovieDataBean movieDataBean) {
                        if(movieDataBean != null ){
                            //总数量
                            int total = movieDataBean.getTotal();
                            //开始下标
                            int start = movieDataBean.getStart();
                            //请求数量
                            int count = movieDataBean.getCount();
                            if(movieDataBean.getSubjects().size() == 0){
                                //没有数据了
                                mView.loadNoMoreData(total+"条数据已经全部加载完了");
                                return;
                            }

                            mView.loadMovieData(movieDataBean.getSubjects(),start+count);
                        }
                    }
                });

    }

    @Override
    public void getMovieComingSoon(int start, int count) {
        OtherHttpApi.getInstace().getMovieComingSoon(start, count)
                .compose(RxUtils.<MovieDataBean>rxSchedulerHelper())
                .compose(mView.<MovieDataBean>bindToLife())
                .subscribe(new BaseObserver<MovieDataBean>(mView) {
                    @Override
                    public void onNext(MovieDataBean movieDataBean) {
                        if (movieDataBean != null) {
                            //总数量
                            int total = movieDataBean.getTotal();
                            //开始下标
                            int start = movieDataBean.getStart();
                            //请求数量
                            int count = movieDataBean.getCount();
                            if(movieDataBean.getSubjects().size() == 0){
                                //没有数据了
                                mView.loadNoMoreData(total+"条数据已经全部加载完了");
                                return;
                            }

                            mView.loadMovieData(movieDataBean.getSubjects(),start+count);
                        }
                    }
                });
    }

    @Override
    public void getMovieTop250(int start, int count) {
        OtherHttpApi.getInstace().getMovie250(start, count)
                .compose(RxUtils.<MovieDataBean>rxSchedulerHelper())
                .compose(mView.<MovieDataBean>bindToLife())
                .subscribe(new BaseObserver<MovieDataBean>(mView) {
                    @Override
                    public void onNext(MovieDataBean movieDataBean) {
                        if(movieDataBean != null ){
                            //总数量
                            int total = movieDataBean.getTotal();
                            //开始下标
                            int start = movieDataBean.getStart();
                            //请求数量
                            int count = movieDataBean.getCount();
                            if(movieDataBean.getSubjects().size() == 0){
                                //没有数据了
                                mView.loadNoMoreData(total+"条数据已经全部加载完了");
                                return;
                            }

                            mView.loadMovieData(movieDataBean.getSubjects(),start+count);
                        }
                    }
                });
    }

    @Override
    public void getMovieWeekly() {
        OtherHttpApi.getInstace().getMovieWeekly()
                .compose(RxUtils.<MovieDataBean>rxSchedulerHelper())
                .compose(mView.<MovieDataBean>bindToLife())
                .subscribe(new BaseObserver<MovieDataBean>(mView) {
                    @Override
                    public void onNext(MovieDataBean movieDataBean) {
                        if(movieDataBean != null ){
                            //总数量
                            int total = movieDataBean.getTotal();
                            //开始下标
                            int start = movieDataBean.getStart();
                            //请求数量
                            int count = movieDataBean.getCount();
                            if(movieDataBean.getSubjects().size() == 0){
                                //没有数据了
                                mView.loadNoMoreData(total+"条数据已经全部加载完了");
                                return;
                            }

                            mView.loadMovieData(movieDataBean.getSubjects(),start+count);
                        }
                    }
                });

    }

    @Override
    public void getMovieSearch(int start, int count, String q, String tag) {
        OtherHttpApi.getInstace().getMovieSearch(start, count, q, tag)
                .compose(RxUtils.<MovieDataBean>rxSchedulerHelper())
                .compose(mView.<MovieDataBean>bindToLife())
                .subscribe(new BaseObserver<MovieDataBean>(mView) {
                    @Override
                    public void onNext(MovieDataBean movieDataBean) {
                        if (movieDataBean != null) {
                            //总数量
                            int total = movieDataBean.getTotal();
                            //开始下标
                            int start = movieDataBean.getStart();
                            //请求数量
                            int count = movieDataBean.getCount();
                            if(movieDataBean.getSubjects().size() == 0){
                                //没有数据了
                                mView.loadNoMoreData(total+"条数据已经全部加载完了");
                                return;
                            }

                            mView.loadMovieData(movieDataBean.getSubjects(),start+count);
                        }
                    }
                });
    }
}
