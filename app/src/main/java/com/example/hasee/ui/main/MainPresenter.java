package com.example.hasee.ui.main;


import com.example.hasee.common.base.mvp.BasePresenter;

import javax.inject.Inject;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/30 09:53
 */

public class MainPresenter extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter {

    private MainService mainService;

    @Inject
    public MainPresenter(MainService loginDataService) {
        this.mainService = loginDataService;
    }

}
