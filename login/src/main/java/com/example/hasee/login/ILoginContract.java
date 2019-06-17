package com.example.hasee.login;

import com.example.hasee.common.base.mvp.IBaseModle;
import com.example.hasee.common.base.mvp.IBasePresenter;
import com.example.hasee.common.base.mvp.IBaseView;
import com.example.hasee.common.net.bean.response.LoginResponce;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;

import io.reactivex.Flowable;

public interface ILoginContract {

    interface IView extends IBaseView{
        void showLoginSucess();

        void showLoginFaile();
    }

    interface IPrensenter extends IBasePresenter<IView>{

        void goToLogin(String username, String password);

        void loginOut();
    }

    interface IModle extends IBaseModle{
        Flowable<WanResponseWapper<LoginResponce>> getLoginData(String username, String password);
    }
}
