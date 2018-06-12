package com.example.hasee.ui.movie;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.http.Common;
import com.example.hasee.ui.adpater.MovieDetailAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Demo ${CLASS}
 *
 * @author TT
 * @date 2018/6/8 17:00
 */

public class MovieDetailFragment extends BaseFragment<MoviePresenter> implements MovieContract.MovieView {

    @BindView(R.id.rv_movie_detail)
    RecyclerView mRvMovieDetail;
    @BindView(R.id.sl_movie)
    SmartRefreshLayout mSlMovie;

    private List<MovieDataBean.SubjectsBean> mSubjectsBeans = new ArrayList<>();
    private MovieDetailAdapter mMovieDetailAdapter;
    private LinearLayoutManager linearLayoutManager;
    private String[] titleList;
    private int position;
    private int start = 0;
    private int count = 20;

    public static MovieDetailFragment newInstance(int position, String[] titleList) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putStringArray("title", titleList);
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getContentLayout() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRvMovieDetail.setLayoutManager(linearLayoutManager);
        mMovieDetailAdapter = new MovieDetailAdapter(getContext(), mSubjectsBeans);
        mRvMovieDetail.setAdapter(mMovieDetailAdapter);

        mSlMovie.setPrimaryColorsId(Common.COLORPRIMARY, R.color.white);
        mSlMovie.setEnableLoadMore(true)
                .setEnableRefresh(true)
                .setEnableAutoLoadMore(true)
                .setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh(RefreshLayout refreshLayout) {
                        mSlMovie.finishRefresh(2500);
                        switch (position) {
                            case 0:
                                mPresenter.getMovieTop250(start, count);
                                break;
                            case 1:
                                mPresenter.getMovieInTheatersData(0, count, "上海");
                                break;
                            case 2:
                                mPresenter.getMovieComingSoon(0, count);
                                break;
                            case 3:
                                mPresenter.getMovieSearch(0, count, "", "喜剧");
                                break;
                            case 4:
                                mPresenter.getMovieSearch(0, count, "", "爱情");
                                break;
                            case 5:
                                mPresenter.getMovieSearch(0, count, "", "动作");
                                break;
                            case 6:
                                mPresenter.getMovieSearch(0, count, "", "科幻");
                                break;
                            case 7:
                                mPresenter.getMovieSearch(0, count, "", "悬疑");
                                break;
                            case 8:
                                mPresenter.getMovieSearch(0, count, "", "动画");
                                break;
                            case 9:
                                mPresenter.getMovieSearch(0, count, "", "剧情");
                                break;
                        }

                    }
                }).setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mSlMovie.finishLoadMore(2500);
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            titleList = getArguments().getStringArray("title");
            position = getArguments().getInt("position");
        }
        showLoadingDialog();
        switch (position) {
            case 0:
                mPresenter.getMovieTop250(start, count);
                break;
            case 1:
                mPresenter.getMovieInTheatersData(0, count, "上海");
                break;
            case 2:
                mPresenter.getMovieComingSoon(0, count);
                break;
            case 3:
                mPresenter.getMovieSearch(0, count, "", "喜剧");
                break;
            case 4:
                mPresenter.getMovieSearch(0, count, "", "爱情");
                break;
            case 5:
                mPresenter.getMovieSearch(0, count, "", "动作");
                break;
            case 6:
                mPresenter.getMovieSearch(0, count, "", "科幻");
                break;
            case 7:
                mPresenter.getMovieSearch(0, count, "", "悬疑");
                break;
            case 8:
                mPresenter.getMovieSearch(0, count, "", "动画");
                break;
            case 9:
                mPresenter.getMovieSearch(0, count, "", "剧情");
                break;


        }

    }

    @Override
    public MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    @Override
    public void loadMovieData(List<MovieDataBean.SubjectsBean> itemBeanList) {
        if (itemBeanList.size() <= 0) {
            return;
        }
        hideLoadingDialog();
        mMovieDetailAdapter.addData(itemBeanList);
    }

    @Override
    public void loadMovieError(String err) {

    }

    @Override
    public void loadNoMoreData(String err) {

    }
}
