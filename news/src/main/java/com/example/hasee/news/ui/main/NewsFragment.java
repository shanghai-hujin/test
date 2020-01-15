package com.example.hasee.news.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hasee.common.base.mvp.XDaggerFragment;
import com.example.hasee.common.net.bean.response.WanHomeBannerResponse;
import com.example.hasee.common.net.bean.response.WanWenZhangResponse;
import com.example.hasee.common.utils.PerfectClickListener;
import com.example.hasee.news.R;
import com.example.hasee.news.adapter.NewsListAdapter;
import com.example.hasee.news.di.NewsDiHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
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
    private BallPulseFooter ballPulseFooter;
    private SmartRefreshLayout srlNews;
    private int pageNum = 0;
    private final int PAGESIZE = 20;
    private int newPageSize = 20;
    private FloatingActionButton fbTop;

    @Override
    public void initViewF(Bundle savedInstanceState) {
        rvNews = (RecyclerView) find(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsListAdapter = new NewsListAdapter(newsDateList);
        newsListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        rvNews.setAdapter(newsListAdapter);
        newsListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            /**
             * Callback method to be invoked when an item in this RecyclerView has
             * been clicked.
             *
             * @param adapter  the adpater
             * @param view     The itemView within the RecyclerView that was clicked (this
             *                 will be a view provided by the adapter)
             * @param position The position of the view in the adapter.
             */
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                WanWenZhangResponse.DatasBean datasBean = (WanWenZhangResponse.DatasBean) adapter.getData().get(position);
                Log.e("tag",position+"");
            }
        });

        ballPulseFooter = (BallPulseFooter) find(R.id.rv_news_foot);
        srlNews = (SmartRefreshLayout) find(R.id.srf_news);
        srlNews.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(newPageSize < PAGESIZE){
                    //加载完成，没有更多数据了
                    srlNews.finishLoadMoreWithNoMoreData();
                }else {
                    pageNum ++;
                    mPresenter.getWanHomeWenZhang(pageNum+"");
                }
                Log.e("onLoadMoreRequested","newPageSize=="+newPageSize);
                Log.e("onLoadMoreRequested","pageNum=="+pageNum);
            }
        });
//        newsListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
//            @Override
//            public void onLoadMoreRequested() {
//                Log.e("onLoadMoreRequested","onLoadMoreRequested");
//            }
//        },rvNews);

        fbTop = (FloatingActionButton) find(R.id.fb_new_top);
        fbTop.setOnClickListener(new PerfectClickListener() {
            @Override
            protected void onNoDoubleClick(View view) {
                rvNews.smoothScrollToPosition(0);
            }
        });
        mPresenter.getWanHomeBanner();
        mPresenter.getWanHomeWenZhang(pageNum+"");
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
        newPageSize = wanWenZhangResponse.getSize();
        if(pageNum == 0){
            newsListAdapter.setNewData(wanWenZhangResponse.getDatas());
        }else {
            newsListAdapter.addData(wanWenZhangResponse.getDatas());
            srlNews.finishLoadMore();
        }
    }
}
