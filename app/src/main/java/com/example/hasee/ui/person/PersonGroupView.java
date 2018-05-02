package com.example.hasee.ui.person;

import com.example.hasee.bean.LoginResponse;
import com.example.hasee.ui.base.BaseContract;

/**
 * Created by HASEE on 2018/4/30.
 */

public interface PersonGroupView {

    interface LoginView extends BaseContract.BaseView{
        void loadData(LoginResponse loginResponse);
    }

    interface  LoginModle{
        interface OnLoginFinishedListener{
            void onUserError();

            void onSuccess();
        }

        void Login(String name, String password, OnLoginFinishedListener loginFinishedListener);
    }
}
