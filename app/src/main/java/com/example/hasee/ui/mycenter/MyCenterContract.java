package com.example.hasee.ui.mycenter;

import com.example.hasee.bean.DataActivityBean;
import com.example.hasee.bean.HistoryTodayBean;
import com.example.hasee.ui.base.BaseContract;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/18 17:34
 */

public interface MyCenterContract {

    interface MyCenterView extends BaseContract.BaseView{


        void loadNullData();

        void loadData(List<DataActivityBean> dataActivityBeanList);

        void loadHittoryData(HistoryTodayBean historyTodayBean);
    }

    interface MyCenterPresenter extends BaseContract.BasePresenter<MyCenterView>{
        void getDateActivity();

        /**
         * 获取历史的今天
         */
        void getHistoruOfToday();
    }
}
