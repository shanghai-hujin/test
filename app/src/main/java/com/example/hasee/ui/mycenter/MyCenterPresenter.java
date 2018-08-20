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

import javax.inject.Inject;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/18 17:24
 */

public class MyCenterPresenter extends BasePresenter<MyCenterContract.MyCenterView> implements MyCenterContract.MyCenterPresenter {


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
    public MyCenterPresenter(OtherHttpApi otherHttpApi) {
        this.otherHttpApi = otherHttpApi;
    }

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
        otherHttpApi.getHistoryOfToday()
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
        otherHttpApi.getNASAOfToday()
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
