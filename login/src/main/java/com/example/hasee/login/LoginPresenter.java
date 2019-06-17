package com.example.hasee.login;

import android.util.Log;

import com.example.hasee.common.base.mvp.BasePresenter;
import com.example.hasee.common.net.NetError;
import com.example.hasee.common.net.bean.response.LoginResponce;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;
import com.example.hasee.common.net.subscriber.BaseSubscriber;
import com.example.hasee.common.net.subscriber.SubscriberListener;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<ILoginContract.IView> implements ILoginContract.IPrensenter {

    private LoginDataService loginDataService;

    @Inject
    public LoginPresenter(LoginDataService loginDataService) {
        this.loginDataService = loginDataService;
    }

    @Override
    public void goToLogin(String username, String password) {

        loginDataService.getLoginData(username,password)
                .compose(getV().<WanResponseWapper<LoginResponce>>bindLifecycle())
                .subscribe(new LoginSubscriber(new LoginSubscriberListener<LoginResponce>() {
                    @Override
                    public void onSuccess(Object response) {
                    }

                    @Override
                    public void onFail(NetError error) {
                        super.onFail(error);
                    }
                }));

    }

    @Override
    public void loginOut() {

    }
}
