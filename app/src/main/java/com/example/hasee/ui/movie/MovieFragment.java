package com.example.hasee.ui.movie;

import android.os.Bundle;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.ui.base.BasePresenter;

/**
 * Created by HASEE on 2018/4/29.
 */

public class MovieFragment extends BaseFragment {
    private View mRootView;

    public static MovieFragment newInstance(String param1){
        Bundle args = new Bundle();
        MovieFragment newsFragment = new MovieFragment();
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
    public void initData() {

    }


}
