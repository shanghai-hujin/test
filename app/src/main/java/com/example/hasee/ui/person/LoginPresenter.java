package com.example.hasee.ui.person;

import com.example.hasee.bean.BaseResponce;
import com.example.hasee.bean.LoginResponse;
import com.example.hasee.http.HttpApi;
import com.example.hasee.ui.base.BaseObserver;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.utils.RxUtils;

/**
 * Created by HASEE on 2018/4/30.
 */

class LoginPresenter extends BasePresenter<PersonGroupView.LoginView>
        implements PersonGroupView.LoginModle,PersonGroupView.LoginModle.OnLoginFinishedListener{


    @Override
    public void Login(String name, String password, OnLoginFinishedListener loginFinishedListener) {
        //这里请求网络
        HttpApi.getInstace().getLoginData(name,password)
                .compose(RxUtils.rxSchedulerHelper())
                .subscribeWith(new BaseObserver<LoginResponse>(mView) {
                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if(loginResponse.getErrorCode() == BaseResponce.SUCCESS){
                            mView.loadData(loginResponse);
                        }
                    }
                });





    }

    @Override
    public void onUserError() {

    }

    @Override
    public void onSuccess() {

    }
}
