package com.example.hasee.ui.news;

import com.example.hasee.bean.Channel;
import com.example.hasee.ui.base.BaseContract;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/4 17:21
 */

public interface NewsContract {

    interface NewsView extends BaseContract.BaseView{

        void loadData(List<Channel> myChannels, List<Channel> otherChannels);
    }

    interface NewsPresenter extends BaseContract.BasePresenter<NewsView>{
        void getChannel();

    }

}
