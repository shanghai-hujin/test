package com.example.hasee.ui.mycenter;

import com.example.hasee.bean.DataActivityBean;
import com.example.hasee.bean.HistoryTodayBean;
import com.example.hasee.bean.MeiRiYiWenBean;
import com.example.hasee.dao.DataActivityDao;
import com.example.hasee.http.OtherHttpApi;
import com.example.hasee.ui.base.BaseObserver;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.utils.RxUtils;

import java.util.List;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/18 17:24
 */

public class MyCenterPresenter extends BasePresenter<MyCenterContract.MyCenterView> implements MyCenterContract.MyCenterPresenter {


    @Override
    public void getDateActivity() {
        //获取本地数据库的数据
        List<DataActivityBean> dataActivityBeanList = DataActivityDao.getDataActivity();

        if (dataActivityBeanList.size() < 1) {
            //空的，等待数据添加
            mView.loadNullData();
        }else {
            //数据库有数据
            mView.loadData(dataActivityBeanList);
        }
    }

    @Override
    public void getHistoruOfToday() {
        OtherHttpApi.getInstace().getHistoryOfToday()
                .compose(RxUtils.<HistoryTodayBean>rxSchedulerHelper())
                .compose(mView.<HistoryTodayBean>bindToLife())
                .subscribe(new BaseObserver<HistoryTodayBean>(mView) {
                    @Override
                    public void onNext(HistoryTodayBean historyTodayBean) {
                        mView.loadHittoryData(historyTodayBean);
                    }
                });

    }

    @Override
    public void getNasaStoryOfToday() {
        OtherHttpApi.getInstace().getNASAOfToday()
                .compose(RxUtils.<MeiRiYiWenBean>rxSchedulerHelper())
                .compose(mView.<MeiRiYiWenBean>bindToLife())
                .subscribe(new BaseObserver<MeiRiYiWenBean>(mView) {
                    @Override
                    public void onNext(MeiRiYiWenBean meiRiYiWenBean) {
                        mView.loadNasaData(meiRiYiWenBean);
                    }
                });

    }
}
