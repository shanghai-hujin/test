package com.example.hasee.ui.movie;

import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.ui.base.BaseContract;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/5 18:55
 */

public interface MovieContract {
    interface MovieView extends BaseContract.BaseView{
        /**
         * 加载电影数据
         *
         * @param itemBeanList
         */
        void loadData(List<MovieDataBean.SubjectsBean> itemBeanList);

        /**
         * 错误信息
         * @param err
         */
        void loadMovieError(String err);


        /**
         * 加载banner  口碑榜的五个数据
         *
         * @param itemBeanList
         */
        void loadBannerData(List<MovieDataBean.SubjectsBean> itemBeanList);
    }

    interface MoviePresenter extends BaseContract.BasePresenter<MovieView>{


        /**
         * 正在上映的电影-城市
         * @param start 开始
         * @param count 数量
         * @param city 城市
         */
        void getMovieInTheatersData(int start, int count, String city );


        /**
         * 即将上映的电影
         * @param start 开始
         * @param count 数量
         */
        void getMovieComingSoon(int start, int count);

        /**
         * Top250
         * @param start 开始
         * @param count 数量
         */
        void getMovieTop250(int start, int count);

        /**
         * 口碑榜
         */
        void getMovieWeekly();

        /**
         * 获取搜索或者tag类型电影
         * @param start 开始
         * @param count 数量
         * @param q 查询
         * @param tag 标签
         */
        void getMovieSearch(int start, int count, String q, String tag);

    }
}
