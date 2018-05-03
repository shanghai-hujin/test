package com.example.hasee.ui.person;

import com.example.hasee.bean.LoginResponse;
import com.example.hasee.ui.base.BaseContract;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/3 11:21
 */

public interface LoginContract {
    interface LoginView extends BaseContract.BaseView{
        void loadData(LoginResponse loginResponse);

        void loginFail(String errormsg);
    }

    interface  LoginPresenter extends BaseContract.BasePresenter<LoginView>{


        void Login(String name, String password);
    }
}
