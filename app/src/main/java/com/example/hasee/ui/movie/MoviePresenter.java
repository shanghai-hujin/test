package com.example.hasee.ui.movie;

import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.http.OtherHttpApi;
import com.example.hasee.ui.base.BaseObserver;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.utils.RxUtils;

import javax.inject.Inject;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/5 18:54
 */

public class MoviePresenter extends BasePresenter<MovieContract.MovieView> implements MovieContract.MoviePresenter{

    OtherHttpApi otherHttpApi;
    /**
     *说明:被inject标记了，会被注入到被 component标记的目标
     * 构造函数中的参数由 module来提供
     * 然后在 component标记的目标中使用  @Inject标记后，直接使用该对象
     *作者:hujin
     *添加时间:2018/8/20 11:07
     *修改人:hujin
     *修改时间:2018/8/20 11:07
     */
    @Inject
    public MoviePresenter(OtherHttpApi otherHttpApi) {
        this.otherHttpApi = otherHttpApi;
    }
    @Override
    public void getMovieInTheatersData(int start, int count, String city) {
        otherHttpApi.getMovieInTheatersData(start, count, city)
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
        otherHttpApi.getMovieComingSoon(start, count)
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
        otherHttpApi.getMovie250(start, count)
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
        otherHttpApi.getMovieWeekly()
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
        otherHttpApi.getMovieSearch(start, count, q, tag)
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
