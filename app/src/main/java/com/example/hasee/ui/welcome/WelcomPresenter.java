package com.example.hasee.ui.welcome;

import com.example.hasee.bean.GanRandomBean;
import com.example.hasee.http.OtherHttpApi;
import com.example.hasee.ui.base.BaseContract;
import com.example.hasee.ui.base.BaseObserver;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.ui.welcome.WelcomeContract.WelcomePresenter;
import com.example.hasee.utils.RxUtils;

import javax.inject.Inject;

/**
 * Created by HASEE on 2019/3/1.
 */

public class WelcomPresenter extends BasePresenter<WelcomeContract.WelcomeView>
        implements WelcomePresenter  {
    OtherHttpApi otherHttpApi;

    @Inject
    public WelcomPresenter(OtherHttpApi otherHttpApi) {
        this.otherHttpApi = otherHttpApi;
    }


    @Override
    public void getGanHuoRandomData(String type, int num) {
        otherHttpApi.getGanRandom(type,3)
                .compose(RxUtils.<GanRandomBean>rxSchedulerHelper())
                .compose(mView.<GanRandomBean>bindToLife())
                .subscribe(new BaseObserver<GanRandomBean>(mView) {
                    @Override
                    public void onNext(GanRandomBean ganRandomBean) {
                        mView.loadPic(ganRandomBean);
                    }
                });
    }
}
