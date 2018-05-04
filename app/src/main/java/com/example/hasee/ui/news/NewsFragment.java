package com.example.hasee.ui.news;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseFragment;

/**
 * Created by HASEE on 2018/4/29.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.NewsView {

    public static NewsFragment newInstance(String param1){
        Bundle args = new Bundle();
        NewsFragment newsFragment = new NewsFragment();
        args.putString("param1",param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_news;
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
