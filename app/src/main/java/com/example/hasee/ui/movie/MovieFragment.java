package com.example.hasee.ui.movie;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.hasee.R;
import com.example.hasee.bean.MovieDataBean;
import com.example.hasee.ui.adpater.MovieHorizontalAdapter;
import com.example.hasee.ui.adpater.MoviePagerAdapter;
import com.example.hasee.ui.base.BaseFragment;
import com.example.hasee.utils.BitmapUtil;
import com.example.hasee.widget.NoScrollViewPager;
import com.example.hasee.widget.recyclebanner.BannerRecyclerView;
import com.example.hasee.widget.recyclebanner.BannerScaleHelper;
import com.flyco.tablayout.SlidingTabLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by HASEE on 2018/4/29.
 */

public class MovieFragment extends BaseFragment<MoviePresenter> implements
        MovieContract.MovieView {

    @BindView(R.id.banner_movie)
    BannerRecyclerView mBannerMovie;
    @BindView(R.id.toolbar_movie)
    Toolbar mToolbarMovie;
    @BindView(R.id.ctl_movie)
    CollapsingToolbarLayout mCtlMovie;
    @BindView(R.id.stl_movie)
    SlidingTabLayout mStlMovie;
    @BindView(R.id.vp_movie)
    NoScrollViewPager mVpMovie;
    @BindView(R.id.image_switcher)
    ImageSwitcher mImageSwitcher;
    @BindView(R.id.tv_bannar_title)
    TextView tv_bannar_title;

    private List<String> mBannerUrlList = new ArrayList<>();
    private List<String> mBannerTitleList = new ArrayList<>();
    private List<MovieDataBean.SubjectsBean> itemBeanList = new ArrayList<>();
    private MovieHorizontalAdapter mMovieHorizontalAdapter;
    private int mCurrentItemOffset;
    private BannerScaleHelper mBannerScaleHelper = null;
    private String[] mTitleLists = {"Top250","正在上映","即将上映","喜剧","爱情",
            "动作","科幻","悬疑","动画","剧情"};

    public static MovieFragment newInstance(String param1) {
        Bundle args = new Bundle();
        MovieFragment newsFragment = new MovieFragment();
        args.putString("param1", param1);
        newsFragment.setArguments(args);
        return newsFragment;
    }

    @Override
    public MoviePresenter createPresenter() {
        return new MoviePresenter();
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_movie;
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mCtlMovie.setExpandedTitleColor(Color.parseColor("#00ffffff"));//设置还没收缩时状态下字体颜色
        initImage();
        initRecyleview();
        initTab();

    }

    /**
     * 订装viewpager
     */
    private void initTab() {
        MoviePagerAdapter moviePagerAdapter = new MoviePagerAdapter(getChildFragmentManager(), mTitleLists);
        mVpMovie.setAdapter(moviePagerAdapter);
        mVpMovie.setOffscreenPageLimit(0);
        mVpMovie.setCurrentItem(0);
        mStlMovie.setViewPager(mVpMovie);
    }

    /**
     * 初始化recyleview
     */
    private void initRecyleview() {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mBannerMovie.setLayoutManager(linearLayoutManager);

        new LinearSnapHelper().attachToRecyclerView(mBannerMovie);
        mMovieHorizontalAdapter = new MovieHorizontalAdapter( getContext(),itemBeanList);
        mBannerMovie.setAdapter(mMovieHorizontalAdapter);

        // mRecyclerView绑定scale效果
        mBannerScaleHelper = new BannerScaleHelper();
        mBannerScaleHelper.setFirstItemPos(1000);
        mBannerMovie.setOnFlingListener(null);
        if(mBannerMovie != null){
            mBannerScaleHelper.attachToRecyclerView(mBannerMovie);
        }
    }

    /**
     * 初始化switchimage
     */
    private void initImage() {
        Animation fadeIn = new AlphaAnimation(0.5f, 1);
        fadeIn.setDuration(500);
        mImageSwitcher.setInAnimation(fadeIn);
        Animation fadeOut = new AlphaAnimation(0.5f, 0);
        fadeOut.setDuration(500);
        mImageSwitcher.setOutAnimation(fadeOut);

        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getActivity());
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setLayoutParams(params);
                return imageView;
            }
        });
    }


    @Override
    public void initData() {
        mPresenter.getMovieWeekly();
    }

    @Override
    public void loadMovieData(List<MovieDataBean.SubjectsBean> itemBeanList, int newStart) {
        if(itemBeanList.size() == 0){
            return;
        }
        //  mMovieHorizontalAdapter.addData(itemBeanList);

        mMovieHorizontalAdapter.setSubjectsBeanList(itemBeanList);
        mMovieHorizontalAdapter.notifyDataSetChanged();

        try {
            Observable<Bitmap> netBitmap = BitmapUtil.getNetBitmap(itemBeanList.get(1000 % itemBeanList.size()).getImages().getMedium());
            netBitmap.subscribe(new Consumer<Bitmap>() {
                @Override
                public void accept(Bitmap bitmap) throws Exception {
                    bitmap = BitmapUtil.fastblur(bitmap,20);
                    mImageSwitcher.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
                }
            });
            tv_bannar_title.setText(itemBeanList.get(1000 % itemBeanList.size()).getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }

        mBannerMovie.setOnPageChangeListener(new BannerRecyclerView.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                try {
                    Observable<Bitmap> netBitmap = BitmapUtil.getNetBitmap(itemBeanList.get(position % itemBeanList.size()).getImages().getMedium());
                    netBitmap.subscribe(new Consumer<Bitmap>() {
                        @Override
                        public void accept(Bitmap bitmap) throws Exception {
                            bitmap = BitmapUtil.fastblur(bitmap,20);
                            mImageSwitcher.setImageDrawable(new BitmapDrawable(getResources(),bitmap));
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("tag", "position=="+position);
                tv_bannar_title.setText(itemBeanList.get(position % itemBeanList.size()).getTitle());
            }
        });

    }

    @Override
    public void loadMovieError(String err) {

    }

    @Override
    public void loadNoMoreData(String err) {

    }

    @Override
    public void loadRefreshMoreData(List<MovieDataBean.SubjectsBean> itemBeanList) {

    }


}
