package com.example.hasee.common.net.bean.wapper;

import com.example.hasee.common.net.subscriber.IResponse;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 玩安卓 的 数据返回类
 * errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
 * errorCode = -1001 代表登录失效，需要重新登录。
 * @param <T>
 */
public class WanResponseWapper<T> implements IResponse<T>, Serializable {

    @SerializedName("data")
    private T data;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    private int errorCode;

    @Override
    public boolean isSuccess() {
        if(errorCode == 0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 根据 后台规定的 错误码去判断
     * @return
     */
    @Override
    public boolean checkReLogin() {
        if(errorCode == -1001){
            return true;
        }
        return false;
    }



    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
