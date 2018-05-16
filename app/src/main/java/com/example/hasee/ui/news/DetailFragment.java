package com.example.hasee.ui.news;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.hasee.R;
import com.example.hasee.bean.NewsDetail;
import com.example.hasee.bean.NewsUtils;
import com.example.hasee.http.Common;
import com.example.hasee.http.NewsHttpApi;
import com.example.hasee.ui.MyApplication;
import com.example.hasee.ui.adpater.NewsDetailAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.utils.ContextUtils;
import com.example.hasee.utils.FrescoUtils;
import com.example.hasee.widget.NewsActionPopup;
import com.example.hasee.widget.SimpleImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.animation.SlideEnter.SlideRightEnter;
import com.flyco.animation.SlideExit.SlideRightExit;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

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
    private View view_Focus;
    private Banner mBanner;
    private boolean isRemoveHeaderView = false;
    private NewsActionPopup newsDelPop;


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
        mNewsDetailAdapter.openLoadAnimation(new BaseAnimation() {
            @Override
            public Animator[] getAnimators(View view) {
                return new Animator[]{
                        ObjectAnimator.ofFloat(view,"scaleY",1, 1.1f,1),
                        ObjectAnimator.ofFloat(view,"scaleX",1, 1.1f,1)
                };
            }
        });
        mRecyclerView.setAdapter(mNewsDetailAdapter);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mRefreshLayout.finishRefresh(2500);
                setRefreshThemeColor();
                isRemoveHeaderView = true;
                mPresenter.getData(mNewsid, NewsHttpApi.ACTION_DOWN,downPullNum);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

                mPresenter.getData(mNewsid, NewsHttpApi.ACTION_UP,upPullNum);
                mRefreshLayout.finishLoadMore(2000);
            }
        });


        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                showError();
            }
        });
        view_Focus = getView().inflate(getActivity(), R.layout.news_detail_headerview, null);
        mBanner = (Banner) view_Focus.findViewById(R.id.banner);
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setImageLoader(new SimpleImageView() {
                    @Override
                    public void displayImage(Context context, Object path, View imageView) {
                        //加载图片
                        FrescoUtils.setController((String) path, ((SimpleDraweeView) imageView));
                    }

                })
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (mNewBannersList.size() < 1) {
                    return;
                }
                bannerToRead(mNewBannersList.get(position));
            }
        });

        newsDelPop = new NewsActionPopup(getActivity())
                .alignCenter(false)
                .widthScale(0.95f)
//                                .showAnim(new FlipRightEnter())
//                                .dismissAnim(new FlipHorizontalExit())
                .showAnim(new SlideRightEnter())
                .dismissAnim(new SlideRightExit())
                .offset(-100, 0)
                .dimEnabled(true);
        newsDelPop.setOnClickListener(new NewsActionPopup.onClickListener() {
            @Override
            public void onClick(int position) {
                newsDelPop.dismiss();
                mNewsDetailAdapter.remove(position);
                showToast(0, false);
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
                            newsDelPop
                                    .anchorView(view)
                                    .gravity(Gravity.TOP)
                                    .setBackAction(itemBean.getStyle().getBackreason(), true, position)
                                    .show();
                        } else {
                            newsDelPop
                                    .anchorView(view)
                                    .gravity(Gravity.BOTTOM)
                                    .setBackAction(itemBean.getStyle().getBackreason(), false, position)
                                    .show();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void showToast(int num, boolean isRefresh) {
        if (isRefresh) {
            Toasty.success(getActivity(), String.format(getResources().getString(R.string.news_toast), num + "")).show();
        } else {
            this.showErrorMsg("将为你减少此类内容");
        }
    }

    private int themeCount = 0;
    private void setRefreshThemeColor() {
        themeCount++;
        if (themeCount % 4 == 1) {
            mRefreshLayout.setPrimaryColorsId(Common.BLUE_THEME, R.color.white);
        } else if (themeCount % 4 == 2) {
            mRefreshLayout.setPrimaryColorsId(Common.GREEN_THEME, R.color.white);
        } else if (themeCount % 4 == 3) {
            mRefreshLayout.setPrimaryColorsId(Common.RED_THEME, R.color.white);
        } else if (themeCount % 4 == 0) {
            mRefreshLayout.setPrimaryColorsId(Common.ORANGE_THEME, R.color.white);
        }
    }

    private void bannerToRead(NewsDetail.ItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        switch (itemBean.getType()) {
            case NewsUtils.TYPE_DOC:
                break;
            case NewsUtils.TYPE_SLIDE:
                break;
            case NewsUtils.TYPE_ADVERT:
                break;
            case NewsUtils.TYPE_PHVIDEO:
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {
        showLoadingDialog();
        if (getArguments() != null) {
            mNewsid = getArguments().getString("newsid");
            mPosition = getArguments().getInt("position");
            mPresenter.getData(mNewsid, NewsHttpApi.ACTION_DEFAULT, 1);
        }
    }

    @Override
    public DetailPresenter createPresenter() {
        return new DetailPresenter();
    }

    @Override
    public void loadBannerData(NewsDetail newsDetail) {
        Logger.e("TAG", "loadBannerData: " + newsDetail.toString());
        List<String> mTitleList = new ArrayList<>();
        List<String> mUrlList = new ArrayList<>();
        mNewBannersList.clear();
        for (NewsDetail.ItemBean bean : newsDetail.getItem()) {
            if (!TextUtils.isEmpty(bean.getThumbnail())) {
                mTitleList.add(bean.getTitle());
                mNewBannersList.add(bean);
                mUrlList.add(bean.getThumbnail());
            }
        }
        if (mUrlList.size() > 0) {
            mBanner.setImages(mUrlList);
            mBanner.setBannerTitles(mTitleList);
            mBanner.start();
            if (mNewsDetailAdapter.getHeaderLayoutCount() < 1) {
                mNewsDetailAdapter.addHeaderView(view_Focus);
            }
        }
    }

    @Override
    public void loadTopNewsData(NewsDetail newsDetail) {
        Logger.e("TAG", "loadTopNewsData: " + newsDetail.toString());
    }

    @Override
    public void loadData(List<NewsDetail.ItemBean> itemBeanList) {
        hideLoadingDialog();
        if (itemBeanList == null) {
            showFaild();
        } else {
            downPullNum++;
            if (isRemoveHeaderView) {
                mNewsDetailAdapter.removeAllHeaderView();
            }
            mNewsDetailAdapter.setNewData(itemBeanList);
            showToast(itemBeanList.size(), true);
            showSuccess(DetailFragment.class.getSimpleName());

        }
    }

    @Override
    public void loadMoreData(List<NewsDetail.ItemBean> itemBeanList) {
        if (itemBeanList == null) {
            mNewsDetailAdapter.loadMoreFail();
        } else {
            upPullNum++;
            mNewsDetailAdapter.addData(itemBeanList);
            mNewsDetailAdapter.loadMoreComplete();
            Log.i("TAG", "loadMoreData: " + itemBeanList.toString());
        }
    }


}
