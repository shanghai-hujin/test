package com.example.hasee.ui.news;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.ui.base.BasePresenter;

/**
 * Created by HASEE on 2018/4/29.
 */

public class NewsFragment extends BaseFragment {
    private View mRootView;

    public static NewsFragment newInstance(String param1){
        Bundle args = new Bundle();
        NewsFragment newsFragment = new NewsFragment();
        args.putString("param1",param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }


    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showError() {

    }
}
