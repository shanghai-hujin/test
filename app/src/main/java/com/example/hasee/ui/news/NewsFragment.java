package com.example.hasee.ui.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.hasee.R;
import com.example.hasee.bean.Channel;
import com.example.hasee.ui.adpater.ChannelPagerAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.widget.NoScrollViewPager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by HASEE on 2018/4/29.
 */

public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.NewsView {

    private static final String TAG = "NewsFragment";
    @BindView(R.id.toolbar_user_avatar)
    SimpleDraweeView mToolbarUserAvatar;
    @BindView(R.id.ll_navigation)
    LinearLayout mLlNavigation;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.stl_tabs)
    SlidingTabLayout mStlTabs;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.iv_edit)
    ImageButton mIvEdit;
    Unbinder unbinder;

    private int selectedIndex;
    private String selectedChannel;
    /**
     * 选中的新闻类型
     */
    private List<Channel> mSelectedDatas;

    /**
     * 未选中的新闻类型
     */
    private List<Channel> mUnSelectedDatas;
    private ChannelPagerAdapter mChannelPagerAdapter;

    public static NewsFragment newInstance(String param1) {
        Bundle args = new Bundle();
        NewsFragment newsFragment = new NewsFragment();
        args.putString("param1", param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    @Override
    public int getContentLayout() {
        return R.layout.layout_news_toolbar;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_me:
                break;
            case R.id.menu_more:
                break;
            case R.id.menu_search:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        if (mToolbar != null) {
            mToolbar.setTitle("");
            ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
            //换成下面这句就OK了
            mToolbar.inflateMenu(R.menu.menu_main);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedIndex = position;
                selectedChannel = mSelectedDatas.get(position).getChannelName();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @Override
    public void initData() {
        mSelectedDatas = new ArrayList<>();
        mUnSelectedDatas = new ArrayList<>();
        mPresenter.getChannel();
    }


    @Override
    public void showErrorMsg(String errorMsg) {

    }

    @Override
    public void showError() {

    }


    @Override
    public void loadData(List<Channel> myChannels, List<Channel> otherChannels) {
        if (myChannels != null) {
            mSelectedDatas.clear();
            mSelectedDatas.addAll(myChannels);

            mUnSelectedDatas.clear();
            mUnSelectedDatas.addAll(otherChannels);

            //装订viewpager
            mChannelPagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(), myChannels);
            mViewPager.setAdapter(mChannelPagerAdapter);
            mViewPager.setOffscreenPageLimit(1);
            mViewPager.setCurrentItem(0);
            mStlTabs.setViewPager(mViewPager);

        } else {
            errToast("数据移除");
        }
    }



    @OnClick({R.id.toolbar_user_avatar, R.id.ll_navigation, R.id.iv_edit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_user_avatar:
                break;
            case R.id.ll_navigation:
                break;
            case R.id.iv_edit:
                break;
        }
    }
}
