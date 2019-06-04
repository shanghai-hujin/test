package com.example.hasee.common.net;

public class NetError extends Exception {


    private Throwable exception;
    private ErrorType errorType;
    public enum ErrorType {
        APParseErrorPS("0","数据解析异常"),

        NoConnectError("-1","无连接异常"),

        AuthError("-2","用户验证异常"),

        NoDataError("-3","无数据返回异常"),

        NetError("-4","网络异常"),

        SocketError("-5","连接超时异常");

        final String eType;
        final String eDescription;

        ErrorType(String type, String description) {
            this.eType = type;
            this.eDescription = description;
        }
    }

    public NetError(String message,ErrorType errorType) {
        super(message);
        this.errorType = errorType;
    }

    public NetError(Throwable cause, ErrorType errorType) {
        this.exception = cause;
        this.errorType = errorType;
    }


    @Override
    public String getMessage() {
        if (exception != null) {
            if (exception.getMessage() == null) {
                return exception.toString();
            } else {
                return exception.getMessage();
            }
        }
        if (super.getMessage() != null) {
            return super.getMessage();
        } else {
            return "未知错误";
        }
    }


}
