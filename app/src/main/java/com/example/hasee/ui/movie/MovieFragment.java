package com.example.hasee.ui.movie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.widget.NoScrollViewPager;
import com.flyco.tablayout.SlidingTabLayout;
import com.youth.banner.Banner;

import butterknife.BindView;

/**
 * Created by HASEE on 2018/4/29.
 */

public class MovieFragment extends BaseFragment<MoviePresenter> implements MovieContract.MovieView {

    @BindView(R.id.banner_movie)
    Banner mBannerMovie;
    @BindView(R.id.toolbar_movie)
    Toolbar mToolbarMovie;
    @BindView(R.id.ctl_movie)
    CollapsingToolbarLayout mCtlMovie;
    @BindView(R.id.stl_movie)
    SlidingTabLayout mStlMovie;
    @BindView(R.id.vp_movie)
    NoScrollViewPager mVpMovie;

    public static MovieFragment newInstance(String param1) {
        Bundle args = new Bundle();
        MovieFragment newsFragment = new MovieFragment();
        args.putString("param1", param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public MoviePresenter createPresenter() {
        return null;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mCtlMovie.setExpandedTitleColor(Color.parseColor("#00ffffff"));//设置还没收缩时状态下字体颜色
    }

    @Override
    public void initData() {

    }

}
