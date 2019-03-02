package com.example.hasee.ui.welcome;

import com.example.hasee.bean.GanRandomBean;
import com.example.hasee.ui.base.BaseContract;

/**
 * Created by HASEE on 2019/3/1.
 */

public interface WelcomeContract {
    interface WelcomeView extends BaseContract.BaseView{
        void loadPic(GanRandomBean ganRandomBean);
    }

    interface WelcomePresenter extends BaseContract.BasePresenter<WelcomeView>{
        void getGanHuoRandomData(String type, int num);
    }
}
