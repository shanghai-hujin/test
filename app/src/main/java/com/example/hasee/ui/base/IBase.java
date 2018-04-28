package com.example.hasee.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/4/26 16:36
 */

public interface IBase {
    /**
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return 返回布局
     */
    View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    View getView();

    /**
     * @return 拿到布局id
     */
    int getContentLayout();


    void bindView(View view, Bundle savedInstanceState);

    void initData();
}
