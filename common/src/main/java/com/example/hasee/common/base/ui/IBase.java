package com.example.hasee.common.base.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface IBase {

    /**
     * 布局id
     * @return
     */
    int getLayoutId();

    /**
     * 当前容器view
     * @return
     */
    View getView();

    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return 返回布局
     */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);


    /**
     * replace  findViewById
     *
     * @param resId   layout resId
     * @param <T>   View
     * @return    View
     */
    <T extends View> T find(int resId) ;


}
