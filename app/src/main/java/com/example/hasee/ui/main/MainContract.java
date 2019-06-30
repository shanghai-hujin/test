package com.example.hasee.ui.main;

import com.example.hasee.common.base.mvp.IBaseModle;
import com.example.hasee.common.base.mvp.IBasePresenter;
import com.example.hasee.common.base.mvp.IBaseView;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/30 09:53
 */

public interface MainContract {

    interface MainView extends IBaseView {

    }

    interface MainPresenter extends IBasePresenter<MainView> {

    }

    interface MainModule extends IBaseModle{

    }
}
