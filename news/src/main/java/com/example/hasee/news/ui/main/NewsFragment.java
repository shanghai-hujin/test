package com.example.hasee.news.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.example.hasee.common.base.mvp.XDaggerFragment;
import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.net.bean.response.WanWenZhangResponse;
import com.example.hasee.news.R;
import com.example.hasee.news.adapter.NewsListAdapter;
import com.example.hasee.news.di.NewsDiHelper;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/home/NewsFragment")
public class NewsFragment extends XDaggerFragment<NewsPresenter> implements INewsContract.IView {

    private Banner mBannerHomeNews;
    private RecyclerView rvNews;
    private List<WanWenZhangResponse.DatasBean> newsDateList = new ArrayList<>();
    private NewsListAdapter newsListAdapter;
    private View headView;

    @Override
    public void initViewF(Bundle savedInstanceState) {
        rvNews = (RecyclerView) find(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsListAdapter = new NewsListAdapter(newsDateList);
        rvNews.setAdapter(newsListAdapter);


        mPresenter.getWanHomeBanner();
        mPresenter.getWanHomeWenZhang("0");
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_news;
    }

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initInject(Bundle savedInstanceState) {
        NewsDiHelper.getFragmentComponent(getFragmentModule()).inject(this);
    }

    @Override
    public void showBannerData(List<WanHomeBannerResponse> response) {
        headView = LayoutInflater.from(getContext()).inflate(R.layout.layout_banner_news, ((ViewGroup) rvNews.getParent()),false);
        mBannerHomeNews = (Banner) headView.findViewById(R.id.banner_news);
        mBannerHomeNews.setImages(response).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(_mActivity).load(((WanHomeBannerResponse) path).getImagePath()).into(imageView);
            }
        }).isAutoPlay(true).start();
        newsListAdapter.addHeaderView(headView);



    }

    /**
     * news 列表
     * @param wanWenZhangResponse
     */
    @Override
    public void showNewsListDate(WanWenZhangResponse wanWenZhangResponse) {
        newsListAdapter.setNewData(wanWenZhangResponse.getDatas());
    }
}
