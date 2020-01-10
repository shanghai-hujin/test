package com.example.hasee.utils;

import com.example.hasee.common.db.DBManager;
import com.example.hasee.common.net.bean.response.LoginResponce;
import com.example.hasee.ui.MyApplication;

/**
 *
 * @author TT
 * @date 2018/5/28
 */

public class AccountInfo {

    public static boolean isLogin(){
        LoginResponce loginResponce = DBManager.getInstance(MyApplication.getInstance()).queryLoginResponce();
        return (loginResponce!= null);
    }

    public static String getHeadUrl(){
       return "https://qlogo3.store.qq.com/qzone/869442114/869442114/100?1563755934";

    }
}
