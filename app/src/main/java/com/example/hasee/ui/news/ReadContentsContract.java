package com.example.hasee.ui.news;

import com.example.hasee.bean.NewsArticleBean;
import com.example.hasee.ui.base.BaseContract;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/30 14:14
 */

public interface ReadContentsContract {

    interface ReadContentsView extends BaseContract.BaseView{

        void loadWebData(NewsArticleBean newsArticleBean);
    }


    interface ReadContentsPresenter extends BaseContract.BasePresenter<ReadContentsView>{
        void getData(String aid);
    }

}
