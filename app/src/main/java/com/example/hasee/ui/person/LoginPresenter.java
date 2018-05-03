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

public class LoginPresenter extends BasePresenter<LoginContract.LoginView>
        implements  LoginContract.LoginPresenter{


    @Override
    public void Login(String name, String password) {
        //这里请求网络
        HttpApi.getInstace().getLoginData(name,password)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(mView.<LoginResponse>bindToLife())
                .subscribe(new BaseObserver<LoginResponse>(mView) {


                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        if(loginResponse.getErrorCode() == BaseResponce.SUCCESS){
                            mView.loadData(loginResponse);
                        }else {
                            mView.loginFail(loginResponse.getErrorMsg());
                        }
                    }
                });




    }


}
