package com.example.hasee.news.hook;

import android.view.View;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private View.OnClickListener mListener;

    public ProxyHandler(View.OnClickListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //处理一些其他工作，比如埋点之类的

        return method.invoke(mListener,args);
    }
}
