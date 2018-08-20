package com.example.hasee.ui.person;

import com.example.hasee.bean.BaseResponce;
import com.example.hasee.bean.LoginResponse;
import com.example.hasee.http.OtherHttpApi;
import com.example.hasee.ui.base.BaseObserver;
import com.example.hasee.ui.base.BasePresenter;
import com.example.hasee.utils.RxUtils;

import javax.inject.Inject;

/**
 * Created by HASEE on 2018/4/30.
 */

public class LoginPresenter extends BasePresenter<LoginContract.LoginView>
        implements  LoginContract.LoginPresenter{

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
    public LoginPresenter(OtherHttpApi otherHttpApi) {
        this.otherHttpApi = otherHttpApi;
    }

    @Override
    public void Login(String name, String password) {
        //这里请求网络
        otherHttpApi.getLoginData(name,password)
                .compose(RxUtils.<LoginResponse>rxSchedulerHelper())
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
