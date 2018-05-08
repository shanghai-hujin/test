package com.example.hasee.ui.news;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hasee.R;
import com.example.hasee.bean.NewsDetail;
import com.example.hasee.ui.base.BaseFragment;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * desc: .
 *
 * @author TT
 */
public class DetailFragment extends BaseFragment<DetailPresenter> implements DetailContract.DetailVew {

    @BindView(R.id.xrv_movie)
    RecyclerView mXrvMovie;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private String mNewsid;
    private int mPosition;

    public static DetailFragment newInstance(String newsid, int position) {
        Bundle args = new Bundle();
        args.putString("newsid", newsid);
        args.putInt("position", position);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_new_detail;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            mNewsid = getArguments().getString("newsid");
            mPosition = getArguments().getInt("position");
            mPresenter.getData(mNewsid, "default", 1);
        }
    }

    @Override
    public DetailPresenter createPresenter() {
        return new DetailPresenter();
    }

    @Override
    public void loadBannerData(NewsDetail newsDetail) {

    }

    @Override
    public void loadTopNewsData(NewsDetail newsDetail) {

    }

    @Override
    public void loadData(List<NewsDetail.ItemBean> itemBeanList) {

    }

    @Override
    public void loadMoreData(List<NewsDetail.ItemBean> itemBeanList) {

    }


}
