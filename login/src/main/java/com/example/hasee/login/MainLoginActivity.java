package com.example.hasee.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.hasee.common.base.mvp.XDaggerActivity;
import com.example.hasee.login.di.LoginDiHelper;

@Route(path = "/app/LoginActivity")
public class MainLoginActivity extends XDaggerActivity<LoginPresenter> implements ILoginContract.IView {

    private Button btnLogin;
    private EditText userName;
    private EditText password;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main_login;
    }


    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        btnLogin = (Button) find(R.id.bt_go);
        userName = (EditText) find(R.id.et_username);
        password = (EditText) find(R.id.et_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainLoginActivity.this, "1111", Toast.LENGTH_SHORT).show();
                mPresenter.goToLogin(userName.getText().toString().trim(),password.getText().toString().trim());
            }
        });
    }

    @Override
    public void initInject(Bundle savedInstanceState) {
        LoginDiHelper.getActivityComponent(getActivityModule()).inject(this);
    }

    @Override
    public void showLoginSucess() {

    }

    @Override
    public void showLoginFaile() {

    }
}
