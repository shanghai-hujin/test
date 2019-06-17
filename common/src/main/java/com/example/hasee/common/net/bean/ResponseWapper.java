package com.example.hasee.common.net.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 出参的包装类
 * @param <Result>
 */
public class ResponseWapper<Result> implements Serializable {
    @SerializedName("success")
    boolean success;
    @SerializedName("result")
    Result result;
    @SerializedName("errorCode")
    String errorCode;
    @SerializedName("errorMsg")
    String errorMsg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
