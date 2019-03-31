package com.example.hasee.ui.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hasee.R;
import com.example.hasee.bean.Channel;
import com.example.hasee.bean.WeatherBean;
import com.example.hasee.dao.ChannelDao;
import com.example.hasee.di.component.DaggerNewsComponent;
import com.example.hasee.di.module.NewsModule;
import com.example.hasee.ui.adpater.ChannelPagerAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.ui.person.TestViewActivity;
import com.example.hasee.utils.Event;
import com.example.hasee.utils.FrescoUtils;
import com.example.hasee.utils.RxBus;
import com.example.hasee.widget.NoScrollViewPager;
import com.example.hasee.widget.dragtab.ChannelDialogFragment;
import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

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
    @BindView(R.id.tv_weather)
    TextView mTvWeather;
    @BindView(R.id.stl_tabs)
    SlidingTabLayout mStlTabs;
    @BindView(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @BindView(R.id.iv_edit)
    ImageButton mIvEdit;
    @BindView(R.id.new_floating_action_btn)
    FloatingActionButton mNewFloatingActionBtn;

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
    private MenuItem mMenuItem;

    private int[] weather = {
            R.mipmap.weather_1, R.mipmap.weather_2, R.mipmap.weather_3,
            R.mipmap.weather_4, R.mipmap.weather_5, R.mipmap.weather_6,
            R.mipmap.weather_7, R.mipmap.weather_8, R.mipmap.weather_9,
            R.mipmap.weather_10, R.mipmap.weather_11, R.mipmap.weather_12,
            R.mipmap.weather_13, R.mipmap.weather_14, R.mipmap.weather_15,
            R.mipmap.weather_16, R.mipmap.weather_17, R.mipmap.weather_18,
            R.mipmap.weather_19, R.mipmap.weather_20, R.mipmap.weather_21,
            R.mipmap.weather_19, R.mipmap.weather_20, R.mipmap.weather_21,
            R.mipmap.weather_22, R.mipmap.weather_23, R.mipmap.weather_24,
            R.mipmap.weather_25, R.mipmap.weather_26, R.mipmap.weather_27,
            R.mipmap.weather_28, R.mipmap.weather_29, R.mipmap.weather_30,
            R.mipmap.weather_31, R.mipmap.weather_32, R.mipmap.weather_33,
            R.mipmap.weather_34, R.mipmap.weather_35, R.mipmap.weather_36,
            R.mipmap.weather_37, R.mipmap.weather_38, R.mipmap.weather_39,

    };

    public static NewsFragment newInstance(String param1) {
        Bundle args = new Bundle();
        NewsFragment newsFragment = new NewsFragment();
        args.putString("param1", param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public NewsPresenter createPresenter() {
        return null;
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
                startActivity(new Intent(getActivity(), TestViewActivity.class));
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

        DaggerNewsComponent.builder()
                .newsModule(new NewsModule(this))
                .build()
                .inject(this);

    }


    @Override
    public void onResume() {
        super.onResume();
        RxBus.INSTANCE.toFlowable(Event.NewChannelEvent.class)
                .compose(bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Event.NewChannelEvent>() {
                    @Override
                    public void accept(Event.NewChannelEvent newChannelEvent) throws Exception {
                        if (newChannelEvent == null) {
                            return;
                        }

                        if (newChannelEvent.selectedDatas != null && newChannelEvent.unSelectedDatas != null) {
                            //传递过来的数据
                            //选中的数据
                            mSelectedDatas = newChannelEvent.selectedDatas;
                            //未选中的数据
                            mUnSelectedDatas = newChannelEvent.unSelectedDatas;
                            //更新
                            mChannelPagerAdapter.updateChannel(mSelectedDatas);
                            mStlTabs.notifyDataSetChanged();
                            //存入到本地
                            ChannelDao.saveChannels(newChannelEvent.allChannels);

                            //设置viewpager的选中页
                            List<String> integers = new ArrayList<>();
                            for (Channel channel : mSelectedDatas) {
                                integers.add(channel.getChannelName());
                            }

                            if (TextUtils.isEmpty(newChannelEvent.firstChannelName)) {
                                if (!integers.contains(selectedChannel)) {
                                    //不包含
                                    selectedChannel = mSelectedDatas.get(selectedIndex).getChannelName();
                                    mViewPager.setCurrentItem(selectedIndex, false);
                                } else {
                                    setViewPagerPoition(integers, selectedChannel);
                                }
                            } else {
                                setViewPagerPoition(integers, selectedChannel);
                            }

                        }
                    }
                });

        RxBus.INSTANCE.toFlowable(Event.SelectChannelEvent.class)
                .compose(bindToLife())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Event.SelectChannelEvent>() {
                    @Override
                    public void accept(Event.SelectChannelEvent selectChannelEvent) throws Exception {
                        if (selectChannelEvent == null) {
                            return;
                        }
                        List<String> integers = new ArrayList<>();
                        for (Channel channel : mSelectedDatas) {
                            integers.add(channel.getChannelName());
                        }
                        setViewPagerPoition(integers, selectChannelEvent.channelName);
                    }
                });
    }

    /**
     * 设置viewpager选中
     *
     * @param integers    我的集合
     * @param channelName 当前
     */
    private void setViewPagerPoition(List<String> integers, String channelName) {
        if (TextUtils.isEmpty(channelName) || integers == null) {
            return;
        }

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i).equals(channelName)) {
                selectedChannel = integers.get(i);
                selectedIndex = i;
                break;
            }
        }

        mViewPager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(selectedIndex, false);
            }
        }, 250);
    }

    @Override
    public void initData() {
        mSelectedDatas = new ArrayList<>();
        mUnSelectedDatas = new ArrayList<>();
      //  mPresenter.getChannel();
      //  mPresenter.getWeather();
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
            mViewPager.setOffscreenPageLimit(0);
            mViewPager.setCurrentItem(0);
            mStlTabs.setViewPager(mViewPager);

        } else {
            errToast("数据移除");
        }
    }

    @Override
    /**
     * 显示天气
     */
    public void showWeather(WeatherBean weatherBean) {
        int code = Integer.parseInt(weatherBean.getResults().get(0).getNow().getCode());
        String url = "res://com.example.hasee/" + weather[code];
        FrescoUtils.setController(url, mToolbarUserAvatar);
        mTvWeather.setText(weatherBean.getResults().get(0).getNow().getText()
                + ": " + weatherBean.getResults().get(0).getNow().getTemperature() + "度");
    }


    @OnClick({R.id.toolbar_user_avatar, R.id.ll_navigation, R.id.iv_edit, R.id.new_floating_action_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_user_avatar:
                break;
            case R.id.ll_navigation:
                //打开侧滑菜单
                Event.StartNavigationEvent event = new Event.StartNavigationEvent();
                event.start = true;
                RxBus.INSTANCE.post(event);
                break;
            case R.id.iv_edit:
                ChannelDialogFragment dialogFragment = ChannelDialogFragment.newInstance(mSelectedDatas, mUnSelectedDatas);
                dialogFragment.show(getChildFragmentManager(), "CHANNEL");
                break;
            case R.id.new_floating_action_btn:
                Event.SlideTopEvent slideTopEvent = new Event.SlideTopEvent();
                slideTopEvent.position = mViewPager.getCurrentItem();
                RxBus.INSTANCE.post(slideTopEvent);
                break;
            default:
                break;
        }
    }



}
