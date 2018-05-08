package com.example.hasee.ui.news;

import com.example.hasee.bean.NewsDetail;
import com.example.hasee.ui.base.BaseContract;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/8 09:57
 */

public interface DetailContract {
    interface DetailVew extends BaseContract.BaseView{

        /**
         * 加载顶部banner数据
         * @param newsDetail
         */
        void loadBannerData(NewsDetail newsDetail);

        /**
         * 加载置顶新闻
         * @param newsDetail
         */
        void loadTopNewsData(NewsDetail newsDetail);

        /**
         * 加载新闻数据
         *
         * @param itemBeanList
         */
        void loadData(List<NewsDetail.ItemBean> itemBeanList);

        /**
         * 加载更多新闻数据
         *
         * @param itemBeanList
         */
        void loadMoreData(List<NewsDetail.ItemBean> itemBeanList);

    }

    interface DetailPresenter extends BaseContract.BasePresenter{

    }
}
