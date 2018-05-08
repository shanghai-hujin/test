package com.example.hasee.ui.news;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.hasee.R;
import com.example.hasee.bean.NewsDetail;
import com.example.hasee.ui.MyApplication;
import com.example.hasee.ui.adpater.NewsDetailAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.utils.ContextUtils;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * desc: .
 *
 * @author TT
 */
public class DetailFragment extends BaseFragment<DetailPresenter> implements DetailContract.DetailVew {

    @BindView(R.id.xrv_movie)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mRefreshLayout;
    private String mNewsid;
    private int mPosition;
    private List<NewsDetail.ItemBean> mNewsList;
    private List<NewsDetail.ItemBean> mNewBannersList;
    private NewsDetailAdapter mNewsDetailAdapter;
    private int upPullNum = 1;
    private int downPullNum = 1;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNewsList = new ArrayList<>();
        mNewBannersList = new ArrayList<>();
        mNewsDetailAdapter = new NewsDetailAdapter(mNewsList, getActivity());
        mRecyclerView.setAdapter(mNewsDetailAdapter);
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                NewsDetail.ItemBean itemBean = (NewsDetail.ItemBean) baseQuickAdapter.getItem(position);
                switch (view.getId()) {
                    case R.id.iv_close:
                        view.getHeight();
                        int[] location = new int[2];
                        view.getLocationInWindow(location);
                        Logger.e("JdDetailFragment", "点击的item的高度:" + view.getHeight() + "x值:" + location[0] + "y值" + location[1]);
                        if (itemBean.getStyle() == null) {
                            return;
                        }
                        if (ContextUtils.getSreenWidth(MyApplication.getContext()) - 50 - location[1] < ContextUtils.dip2px(MyApplication.getContext(), 80)) {
                           /* newsDelPop
                                    .anchorView(view)
                                    .gravity(Gravity.TOP)
                                    .setBackReason(itemBean.getStyle().getBackreason(), true, i)
                                    .show();*/
                        } else {
                            /*newsDelPop
                                    .anchorView(view)
                                    .gravity(Gravity.BOTTOM)
                                    .setBackReason(itemBean.getStyle().getBackreason(), false, i)
                                    .show();*/
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        showLoadingDialog();
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
        hideLoadingDialog();
        if (itemBeanList == null) {
            showFaild();
        } else {
            downPullNum++;
           /* if (isRemoveHeaderView) {
                detailAdapter.removeAllHeaderView();
            }*/
            mNewsDetailAdapter.setNewData(itemBeanList);
          //  showToast(itemBeanList.size(), true);
           // mPtrFrameLayout.refreshComplete();
         //   showSuccess();
         //   Log.i(TAG, "loadData: " + itemBeanList.toString());
        }
    }

    @Override
    public void loadMoreData(List<NewsDetail.ItemBean> itemBeanList) {

    }


}
