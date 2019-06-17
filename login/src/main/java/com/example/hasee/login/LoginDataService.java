package com.example.hasee.login;

import com.example.hasee.common.net.HttpHelper;
import com.example.hasee.common.net.bean.request.LoginRequest;
import com.example.hasee.common.net.bean.response.LoginResponce;
import com.example.hasee.common.net.bean.wapper.WanResponseWapper;
import com.example.hasee.common.utils.RxUtils;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * 登陆
 * api service类
 */
public class LoginDataService implements ILoginContract.IModle {

    private HttpHelper httpHelper;

    /**
     *说明:被inject标记了，会被注入到被 component标记的目标
     * 构造函数中的参数由 module来提供
     * 然后在 component标记的目标中使用  @Inject标记后，直接使用该对象
     * 可以多个一起
     *作者:hujin
     *添加时间:2018/8/20 11:07
     *修改人:hujin
     *修改时间:2018/8/20 11:07
     */
    @Inject
    public LoginDataService(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public Flowable<WanResponseWapper<LoginResponce>> getLoginData(String username, String password) {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        Flowable<WanResponseWapper<LoginResponce>> compose = httpHelper.createApi(LoginApi.class).getLoginData(username,password)
                .compose(RxUtils.<WanResponseWapper<LoginResponce>>rxSchedulerHelperNoRetry());
        return compose;
    }
}
