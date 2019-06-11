package com.example.hasee.common.net.bean;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/5/2 11:53
 */

public class BaseResponce {

    /**
     * 成功code
     */
    public static final int SUCCESS = 0;

    /**
     * 失败code
     */
    public static final int FAIL = 1;

    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private String errorMsg;


}
