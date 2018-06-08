package com.example.hasee.ui.movie;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.ui.adpater.MovieDetailAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

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

    public static MovieDetailFragment newInstance(int position) {
        Bundle args = new Bundle();
        args.putInt("position", position);
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
        //由于不可避免的滑动冲突
        mSlMovie.setEnableRefresh(false);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mRvMovieDetail.setLayoutManager(linearLayoutManager);
        mMovieDetailAdapter = new MovieDetailAdapter(getContext(),mSubjectsBeans);
        mRvMovieDetail.setAdapter(mMovieDetailAdapter);

    }

    @Override
    public void initData() {
        mPresenter.getMovieTop250(0, 20);
    }

    @Override
    public MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    @Override
    public void loadMovieData(List<MovieDataBean.SubjectsBean> itemBeanList) {
        if(itemBeanList.size() <= 0){
            return;
        }
        mMovieDetailAdapter.addData(itemBeanList);
    }

    @Override
    public void loadMovieError(String err) {

    }
}
