package com.example.hasee.login;

import com.example.hasee.common.base.mvp.IBaseModle;
import com.example.hasee.common.base.mvp.IBasePresenter;
import com.example.hasee.common.base.mvp.IBaseView;

import io.reactivex.Flowable;

public interface ILoginContract {

    interface IView extends IBaseView{
        void showLoginSucess();

        void showLoginFaile();
    }

    interface IPrensenter extends IBasePresenter<IView>{

        void goToLogin();

        void loginOut();
    }

    interface IModle extends IBaseModle{
        Flowable<Object> getLoginData();
    }
}
