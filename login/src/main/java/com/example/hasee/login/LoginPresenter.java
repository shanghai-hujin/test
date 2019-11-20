package com.example.hasee.login;

import com.example.hasee.common.base.mvp.BasePresenter;
import com.example.hasee.common.net.NetError;
import com.example.hasee.common.net.bean.response.LoginResponce;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<ILoginContract.IView> implements ILoginContract.IPrensenter {

    private LoginDataService loginDataService;

    /**
     * modle未提供，直接构造
     * @param loginDataService
     */
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
                    public void onSuccess(LoginResponce response) {
                        getV().showLoginSucess(response);

                    }

                    @Override
                    public void onFail(NetError error) {
                        super.onFail(error);
                        getV().showLoginFaile(error.getMessage());
                    }
                }));

    }

    @Override
    public void loginOut() {

    }
}
